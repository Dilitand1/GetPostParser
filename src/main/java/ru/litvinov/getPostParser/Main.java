package ru.litvinov.getPostParser;

import org.json.JSONObject;
import ru.litvinov.getPostParser.FSSPparser.models.result.ErrorResult;
import ru.litvinov.getPostParser.FSSPparser.models.result.GetResult;
import ru.litvinov.getPostParser.FSSPparser.models.result.ResponseResult;
import ru.litvinov.getPostParser.FSSPparser.models.result.ResultResult;
import ru.litvinov.getPostParser.utils.fileUtils.FileUtils;
import ru.litvinov.getPostParser.utils.jsonUtils.JsonUtils;

import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class Main {

    private static String outputSuccessFile = "outputSuccessFile.txt";

    public static void main(String[] args) throws Exception {
        String s = FileUtils.readFile("json2.txt");
        GetResult getResult = (GetResult) JsonUtils.jsonToObject(s, GetResult.class);
        System.out.println(getResult.getStatus());
    }

}

