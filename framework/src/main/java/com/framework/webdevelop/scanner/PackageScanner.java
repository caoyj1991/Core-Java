package com.framework.webdevelop.scanner;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * Author Jun
 * Email
 * Date   6/25/17
 * Time   2:31 PM
 */
public class PackageScanner {



    public List<String> getClassNames(String ...paths){
        List<String> fileNames = scan(paths);
        List<String> clazzNames = new ArrayList<>();
        if(fileNames!=null && fileNames.size()>0){
            String projectPath = getClass().getResource("/").getPath();
            for (int i=0;i<fileNames.size();i++){
                clazzNames.add(fileNames.get(i).replace(projectPath,"").replace("/",".").replace(".class",""));
            }
        }
        return clazzNames;
    }

    public List<String> scan(String ...paths){
        List<String> fileNames = new ArrayList<>();
        if(paths == null || paths.length <= 0){
            return Collections.emptyList();
        }
        for(int i=0;i<paths.length;i++){
            try {
                String item = paths[i].replace(".","/");
                Enumeration<URL> enumeration =Thread.currentThread().getContextClassLoader().getResources(item);
                while (enumeration.hasMoreElements()){
                    URL url = enumeration.nextElement();
                    if(url.getProtocol().equals("file")) {
                        fileScanner(url.getPath(), fileNames);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileNames;
    }

    private List<String> fileScanner(String path, List<String> fileNames){
        File file = new File(path);
        if(!file.exists()){
            return fileNames;
        }
        if(file.isDirectory()){
            File[] dirFiles = file.listFiles();
            for (int i=0;i<dirFiles.length;i++){
                fileScanner(dirFiles[i].getAbsolutePath(), fileNames);
            }
        }else if(file.isFile()){
            fileNames.add(file.getAbsolutePath());
        }
        return fileNames;
    }

    public static void main(String[] args){
        new PackageScanner().getClassNames("com.network");
    }
}
