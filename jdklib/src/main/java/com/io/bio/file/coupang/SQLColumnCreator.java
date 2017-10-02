package com.io.bio.file.coupang;

import com.io.bio.file.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author Mr.Pro
 * Date   9/20/17 = 9:43 PM
 */
public class SQLColumnCreator {

    public static void main(String[] args) throws IOException {
        String data = FileUtils.readFile("test.txt");
        String lines[] = data.split("\n");
        String temp = "";
        String table = "user";

        for (String line : lines){
            String c = line.split(",")[0];
            temp+="\n"+c+",";
        }
        temp = "SELECT "+temp.substring(0, temp.length()-1)+" FROM "+table;

        System.out.println(temp);
        System.out.println();
        for (String line : lines){
            String key = line.split(",")[0].replace("'","");
            String type = line.split(",")[1];
            if(type.contains("int")){
                type = "Integer";
            }else{
                type = "String";
            }
            System.out.println("private "+type+" "+key+";");
        }

        temp = "";
        String temp2 = "";
        for (String line : lines){
            String c = line.split(",")[0];
            temp+="\n"+c+",";
            if(c.contains("startD")){
                c = "SYSDATE()";
            }else if(c.contains("endD")){
                c = "NULL";
            }else{
                c = "#{"+c.replace("'","")+"}";
            }
            temp2 += "\n"+c+",";
        }
        temp = "INSERT INTO "+table+"("+temp.substring(0, temp.length()-1)+") \nVALUES("+temp2.substring(0,temp2.length()-1)+")";

        System.out.println();
        System.out.println(temp);
        System.out.println();

        String where = null;
        StringBuffer t = new StringBuffer();
        for (String line : lines){

            String key = line.split(",")[0];
            if(where == null){
                where = key +" = #{"+key.replace("'","")+"}";
                continue;
            }
            t.append("\n"+key +" = #{"+key.replace("'","")+"},");
        }
        temp = "UPDATE "+table+"\nSET "+t.toString().substring(0,t.toString().length()-1)+" \nWHERE \n"+where;
        System.out.println();
        System.out.println(temp);
        System.out.println();
    }
}
