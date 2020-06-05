package ru.litvinov.getPostParser;

import ru.litvinov.getPostParser.utils.fileUtils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static String outputSuccessFile = "outputSuccessFile.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("ðÓÑ");
    }

    public static void deleteDownloaded(){
        String s = FileUtils.readFile("inputFile.txt","Windows-1251");
        String[] tmpArray = s.split("\n");
        List<String> strings = Arrays.asList(tmpArray);
    }

    public static void allregions() throws IOException {
        String s = FileUtils.readFile("tmp.txt","Windows-1251");
        String[] tmpArray = s.split("\n");
        List<String> strings = Arrays.asList(tmpArray);

        for (int j = 0; j < strings.size(); j++) {
            for (int i = 1; i <= 100; i++) {
                FileUtils.writeFile(strings.get(j).trim() + ";" + i + "\n","outputTmp.txt",true);
            }
        }
    }
}

