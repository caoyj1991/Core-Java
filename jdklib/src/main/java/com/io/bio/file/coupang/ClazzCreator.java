package com.io.bio.file.coupang;

import com.io.bio.file.FileUtils;

import java.io.IOException;

/**
 * Author Mr.Pro
 * Date   9/19/17 = 1:35 PM
 */
public class ClazzCreator {

    public static void main(String args[]) throws IOException {
        String data = FileUtils.readFile("test.txt");
        String lines[] = data.split("\n");
        for (String line : lines){
            if (line.startsWith("//"))
                continue;
            String[] lineData = line.split(":");
            String key = lineData[0].replace("\"","");
            String type = null;
            String value = null;
            try{
                value = lineData[1].split(",")[0].trim();
                if(value.indexOf("//")!=-1){
                    value = value.split("//")[0].trim();
                }
            }catch (Exception e){
//                e.printStackTrace();
            }
            while (value != null){
                Object temp = null;
                try{
                    value = value.toLowerCase();
                    if(value.equals("true")
                            || value.equals("false")) {
                        type = "Boolean";
                        break;
                    }
                }catch (Exception e){
//                    e.printStackTrace();
                }

                try{
                    temp = Integer.valueOf(value);
                    type = "Integer";
                    break;
                }catch (Exception e){
//                    e.printStackTrace();
                }
                try{
                    temp = Long.valueOf(value);
                    type = "Long";
                    break;
                }catch (Exception e){
//                    e.printStackTrace();
                }

                try{
                    temp = String.valueOf(value);
                    type = "String";
                    break;
                }catch (Exception e){
//                    e.printStackTrace();
                }
            }
            System.out.println("private "+type+" "+key+"; ");
        }
    }
}
