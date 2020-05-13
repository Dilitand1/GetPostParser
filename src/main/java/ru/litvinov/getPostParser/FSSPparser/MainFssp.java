package ru.litvinov.getPostParser.FSSPparser;

import ru.litvinov.getPostParser.FSSPparser.core.Logic;
import ru.litvinov.getPostParser.FSSPparser.core.LogicIp;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class MainFssp {

    static String token = "ZYrCH4sHnb89";
    static String token2 = "2oFwTlKyEa92";
    static String testIp = "93183/18/38035-ИП";
    static String test_uid = "36046182-705e-4183-9338-be8959da80e1";

    static String tttbefore = "https://api-ip.fssprus.ru/api/v1.0/search/ip?token=ZYrCH4sHnb89&number=93183/18/38035-ИП";

    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
        Logic logic = new LogicIp(token2);

        //GetResponse result = (GetResponse) logic.sendGet(testIp);
    }
}
