package com.e2e.config.utilities;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class IOUtils {
    public IOUtils() {
    }

    public static void saveStringToFile(String strToSave, String fileName) {
        try {
            FileUtils.writeStringToFile(new File(fileName), strToSave, Charset.defaultCharset());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String loadFileToString(String fileName) {
        String content = "";
        try {
            content = FileUtils.readFileToString(new File(fileName), Charset.defaultCharset());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
