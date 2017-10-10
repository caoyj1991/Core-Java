package com.utils.entity.creator;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author Mr.Pro
 * Date   10/10/17 = 8:07 AM
 */
public class SqlTranslator {

    private Entity currentEntity;
    private Status status = Status.FIND_ENTITY;

    enum DBDataType{
        INT("Integer"),
        VARCHAR("String"),
        TIMESTAMP("String"),
        TEXT("String");

        private String value;
        DBDataType(String value){
            this.value = value;
        }

        public String getValue(){
            return this.value;
        }

        public static String classType(String name){
            return DBDataType.valueOf(name).getValue();
        }

    }

    enum Status{
        HAS_ENTITY,
        FIND_ENTITY
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    class Entity{
        private String clazzName;
        private Map<String, String> variables;

        public void addNewVariable(String key, String value){
            if(key != null
                    && !key.equals("")){
                if(variables == null){
                    this.variables = new LinkedHashMap<>();
                }
                this.variables.put(key, value);
            }
        }
    }

    private Entity createNewEntity(String line){
        String temp = line.toLowerCase();
        temp = temp.replace("create table", "").trim();
        temp = temp.replace("if not exists", "").trim();
        temp = temp.replace("(", "").trim();
        temp = temp.replace("`","").trim();
        String[] name =temp.split("\\.");

        return new Entity()
                .setClazzName(name.length>1 ? name[1] : name[0]);
    }

    private void readVariable(String line){
        String temp = line.trim();
        String variableName = "";
        String classType = "";
        if(temp.startsWith("`")){
            String[] o = temp.split(" ");
            variableName = o[0].replace("`","").trim();
            String dataType = o[1];
            classType = transformDBDataType(dataType);
        }
//        Map<String, String> variables = currentEntity.getVariables();
        currentEntity.addNewVariable(variableName, classType);
    }

    private String transformDBDataType(String dataType){
        if(dataType.contains("(")){
            dataType = dataType.substring(0, dataType.indexOf("(")).toUpperCase();
        }
        return DBDataType.classType(dataType.replace(";",""));
    }

    public List<Entity> parsingClassObject(String sqlQuery){
        List<Entity> entities = new ArrayList<>();
        Map<String, Entity> maps = new HashMap<>();
        String[] lines = sqlQuery.split("\n");

        for (String line : lines){
            if(line.toLowerCase().contains("create table") && status == Status.FIND_ENTITY){
                currentEntity = createNewEntity(line);
                status = Status.HAS_ENTITY;
                continue;
            }
            if(status == Status.HAS_ENTITY){
                if(line.contains(";")){
                    status = Status.FIND_ENTITY;
                    maps.put(currentEntity.getClazzName(), currentEntity);
                }else {
                    readVariable(line);
                }
            }else if(status == Status.FIND_ENTITY){
                if(line.toUpperCase().startsWith("ALTER TABLE")){
                    String splitKey = "";
                    if(line.contains("ADD COLUMN")){
                        splitKey = "ADD COLUMN";

                    }else if (line.contains("MODIFY")){
                        splitKey = "MODIFY";
                    }
                    String[] temp = line.split(splitKey);
                    String tableName = (temp[0].replace("ALTER TABLE", "").trim().split("\\."))[1];
                    String valueName = (temp[1].trim().split(" "))[0];
                    String classType = transformDBDataType((temp[1].trim().split(" "))[1]);
                    Entity entity = maps.get(tableName);
                    entity.addNewVariable(valueName, classType);
                    maps.put(tableName, entity);
                }
            }
        }
        entities = maps.entrySet().stream().map(input->input.getValue()).collect(Collectors.toList());
        return entities;
    }

}
