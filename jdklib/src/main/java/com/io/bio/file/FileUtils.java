package com.io.bio.file;

import java.io.*;
import java.nio.file.NoSuchFileException;

/**
 * Author Mr.Pro
 * Date   9/10/17 = 4:01 PM
 */
public class FileUtils {

    public static String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        if(!file.exists()){
            throw new NoSuchFileException("Can not find "+filePath);
        }
        FileReader fileReader = new FileReader(filePath);
        int c;
        StringBuffer text = new StringBuffer();
        while ((c=fileReader.read())!=-1){
            char cc = (char)c;
            text.append(cc);
        }
        return text.toString();
    }
}
