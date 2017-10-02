package com.io.bio.file.coupang;

import com.io.bio.file.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Author Mr.Pro
 * Date   9/28/17 = 2:13 PM
 */
public class EnumReader {
    public static void main(String[] arg) throws IOException {
        String data = FileUtils.readFile("mock.txt");
        String[] lines = data.split("\n");
        String temp = "new String []{";
        for (String line : lines){
            try {
                temp += "\""+line.split(" ")[0] +"\",";
            }catch (Exception e){

            }
        }
        temp = temp.substring(0, temp.length()-1);
        temp+="};";
        System.out.println("String[] deliveryMethod = "+temp);
    }
}
