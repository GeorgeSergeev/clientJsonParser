package com.alvion.importer.reader.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileUtil {

    public static List<File> getFiles(String path){
        Collection files = FileUtils.listFiles(new File(path), null, false);
        return new ArrayList<>(files);
    }

    public static File getFile(String path, String name){
        return new File(path + name + ".json");
    }

    public static void saveToFile(String path, String name, String value) throws IOException {
        try(FileWriter writer = new FileWriter(path + "/" + name + ".json")) {
            writer.write(value);
        }
    }
}
