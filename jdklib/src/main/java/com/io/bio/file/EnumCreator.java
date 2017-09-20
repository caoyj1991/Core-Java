package com.io.bio.file;

import java.io.IOException;

/**
 * Author Mr.Pro
 * Date   9/19/17 = 1:56 PM
 */
public class EnumCreator {

    public static void main(String args[]) throws IOException {
        String data = FileUtils.readFile("test.txt");
        String[] lines = data.split("\n");
        for (String line : lines){
            String type = line.split(",")[0].trim().toUpperCase();
            System.out.println(type+"(\""+type+"\"),");
        }
    }
}
