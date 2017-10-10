package com.utils.entity.creator;

import com.io.bio.file.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Author Mr.Pro
 * Date   10/10/17 = 12:31 PM
 */
public class EntityFactory {

    private List<SqlTranslator.Entity> entities;
    private String packageName;
    private String projectDir;

    public EntityFactory(List<SqlTranslator.Entity> entities, String projectDir,String packageName){
        this.entities = entities;
        this.packageName = packageName;
        this.projectDir = projectDir;
    }

    public void build(){
        String pgName = this.packageName;
        if(pgName == null){
            pgName = "";
        }else{
            pgName = pgName.trim().replace(".","/");
        }
        if (this.projectDir != null
                &&this.projectDir.endsWith("/")){
            this.projectDir = this.projectDir.substring(0, this.projectDir.length()-1);
        }
        String path = (!this.projectDir.equals("")?this.projectDir+"/":"")+pgName;
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        entities.forEach(input->{
            try {
                buildJavaFile(path, input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void buildJavaFile(String path,SqlTranslator.Entity entity) throws IOException {
        String className = entity.getClazzName().toUpperCase().substring(0,1)+entity.getClazzName().substring(1, entity.getClazzName().length());

        String fileString = buildPackage();
        fileString += "public class "+className+" {\n";
        fileString += buildVariables(entity.getVariables());
        fileString += "\n}\n";
        FileUtils.write(path, className+".java", fileString);
    }

    private String buildPackage(){
        return "package "+this.packageName+"\n\n";
    }

    private String buildVariables(Map<String, String> variables){
        String result = "";
        for (String key : variables.keySet()){
            String line = "\n\tprivate "+variables.get(key)+" "+key+";";
            result += line;
        }
        return result;
    }
}
