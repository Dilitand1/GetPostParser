package ru.litvinov.getPostParser.FSSPparser;

import ru.litvinov.getPostParser.FSSPparser.core.CacheWorkerIp;
import ru.litvinov.getPostParser.FSSPparser.core.CoreFssp;
import ru.litvinov.getPostParser.FSSPparser.core.CoreFsspIp;
import ru.litvinov.getPostParser.FSSPparser.core.LogicIp;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainFssp {

    static String token = "ZYrCH4sHnb89";
    static String token2 = "2oFwTlKyEa92";

    public static void main(String[] args) throws Exception {
        CoreFssp core = new CoreFsspIp(token2, new LogicIp());
        core.setInputFile("testfile.txt");
        core.setCacheWork(new CacheWorkerIp());
        core.getCacheWork().init();
        core.sendPosts();
    }
}
