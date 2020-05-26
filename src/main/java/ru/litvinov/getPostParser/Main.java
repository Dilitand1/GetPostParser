package ru.litvinov.getPostParser;


import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static String outputSuccessFile = "outputSuccessFile.txt";

    public static void main(String[] args) throws Exception {
        System.out.println(Files.exists(Paths.get("src\\main\\resources\\notariat.properties")));
    }

}

