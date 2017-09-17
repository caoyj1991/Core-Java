package com.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.io.bio.file.FileUtils;

import java.io.IOException;
import java.util.Map;
import java.util.function.Function;

/**
 * Author Mr.Pro
 * Date   9/11/17 = 11:14 AM
 */
public class HttpResponseUtil {

    public static void main(String[] arg) throws IOException {
        String jsonString = FileUtils.readFile("test.txt");
        JSONObject json = JSON.parseObject(jsonString);
        json.entrySet().stream().forEach(item ->{
            System.out.println(item.getKey());
        });

        json.getJSONObject("rData").entrySet().stream().forEach(item ->{
            System.out.println(item.getKey());
        });
    }
}
