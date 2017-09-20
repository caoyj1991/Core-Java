package com.io.bio.file;

import java.io.IOException;

/**
 * Author Mr.Pro
 * Date   9/20/17 = 11:18 PM
 */
public class InitializedCreator {

    public static void main(String[] args) throws IOException {
        String data = FileUtils.readFile("test.txt");
        String lines[] = data.split("\n");
        String temp = "";

        for (String line : lines){
            String c = line.split(" ")[2].replace(";","");
            String type = line.split(" ")[1];
            String t = "o.get(\""+c+"\")";
            if(type.equals("Integer")){
                t = "Integer.valueOf("+t+".toString())";
            }else{
                t = t+".toString()";
            }
            System.out.println(".set"+c.substring(0,1).toUpperCase()+c.substring(1, c.length())+"("+t+")");
        }

    }
}
