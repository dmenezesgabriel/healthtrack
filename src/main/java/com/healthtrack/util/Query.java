package com.healthtrack.util;

import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.io.IOException;

public class Query {

    public static String fileToString(String queryFile) {
        Path path = new File(Query.class.getResource("/scripts/database" + queryFile).getFile()).toPath();
        String sql = null;
        try {
            sql = Files.readString(path);
        } catch (IOException error) {
            error.printStackTrace();
        } catch (Exception error) {
            error.printStackTrace();
        }
        return sql;
    }
}