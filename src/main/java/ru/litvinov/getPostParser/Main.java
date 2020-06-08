package ru.litvinov.getPostParser;

import ru.litvinov.getPostParser.utils.fileUtils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static String outputSuccessFile = "outputSuccessFile.txt";

    public static void main(String[] args) throws Exception {
        allregions();
    }

    public static void print() throws IOException, InterruptedException {
        List<String> list = Files.readAllLines(Paths.get("inputFile.txt"));
        for(String s : list){
            System.out.println(s);
            Thread.sleep(1000);
        }
    }

    public static void deleteDownloaded(){
        String s = FileUtils.readFile("inputFile.txt");
        String[] tmpArray = s.split("\n");
        List<String> strings = Arrays.asList(tmpArray);
    }

    public static void allregions() throws IOException, InterruptedException {
        String s = FileUtils.readFile("tmp.txt");
        String[] tmpArray = s.split("\n");
        List<String> strings = Arrays.asList(tmpArray);

        for (int j = 0; j < strings.size(); j++) {
            for (int i = 1; i <= 100; i++) {
                //Thread.sleep(1000);
                FileUtils.writeFile(strings.get(j).trim() + ";" + i + "\n","outputTmp.txt",true);
            }
        }
    }
}

