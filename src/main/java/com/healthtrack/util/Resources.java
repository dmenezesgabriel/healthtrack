package com.healthtrack.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Resources {
    public static FileReader readFile(String filePath) {
        File resourceFile = new File(Resources.class.getResource(filePath).getFile());
        FileReader resourceContent = null;
        try {
            FileReader fReader = new FileReader(resourceFile);
            resourceContent = fReader;
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        }
        return resourceContent;

    }

}
