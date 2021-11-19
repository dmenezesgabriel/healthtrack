package com.healthtrack.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Resources {
    public static Properties readProperties(String filePath) {
        FileReader fReader = null;
        Properties props = null;
        File resourceFile = new File(Resources.class.getResource(filePath).getFile());
        try {
            fReader = new FileReader(resourceFile);
            props = new Properties();
            props.load(fReader);
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        } catch (IOException error) {
            error.printStackTrace();
        } finally {
            try {
                fReader.close();
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
        return props;
    }
}
