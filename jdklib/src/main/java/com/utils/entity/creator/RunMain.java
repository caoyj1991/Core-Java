package com.utils.entity.creator;

import com.io.bio.file.FileUtils;

import java.io.IOException;
import java.util.List;

/**
 * Author Mr.Pro
 * Date   10/10/17 = 12:31 PM
 */
public class RunMain {

    public static void main(String[] args) throws IOException {
        String sqlQuery = FileUtils.readFile("disney.sql");
        List<SqlTranslator.Entity> entities = new SqlTranslator().parsingClassObject(sqlQuery);
        EntityFactory entityFactory = new EntityFactory(entities, "/Users/caoyongjun/project/open-project", "com.test.test");
        entityFactory.build();
    }
}
