package ru.litvinov.getPostParser.FSSPparser;

import ru.litvinov.getPostParser.FSSPparser.core.Logic;
import ru.litvinov.getPostParser.FSSPparser.core.LogicIp;
import ru.litvinov.getPostParser.FSSPparser.models.getResponse.GetResponse;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.ParamsIp;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.PostRequest;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.Request;
import ru.litvinov.getPostParser.utils.jsonUtils.JsonUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainFssp {

    static String token = "ZYrCH4sHnb89";
    static String token2 = "2oFwTlKyEa92";
    static String testIp = "93183/18/38035-ИП";
    static String test_uid = "081fc33b-106d-4ef2-982d-77a47da3f18a";

    static String tttbefore = "https://api-ip.fssprus.ru/api/v1.0/search/ip?token=ZYrCH4sHnb89&number=93183/18/38035-ИП";

    public static void main(String[] args) throws IOException, URISyntaxException {
        Logic logic = new LogicIp(token2);
        List<String> strings = Files.readAllLines(Paths.get("testfile.txt"));
        List<PostRequest> postRequests = createListObjectsForPost(strings);
        for(PostRequest p : postRequests){
            System.out.println(JsonUtils.objectToJson(p));
        }
        //GetResponse result = (GetResponse) logic.sendGet(testIp);
    }

    public static List<PostRequest> createListObjectsForPost(List inputList) {
        List<PostRequest> postRequests = new ArrayList<>();
        //режем на части по 50 штук.
        for (int i = 0;i <= inputList.size()/50; i++){
            PostRequest postRequest = new PostRequest();
            postRequest.setToken(token2);
            List<Request> requests = new ArrayList<>();
            int counter = 0;
            for (int j = i*50; j < inputList.size() && counter != 50 ;j++, counter++){
                Request request1 = new Request();
                ParamsIp paramsIp = new ParamsIp();
                paramsIp.setNumber(inputList.get(j).toString());
                request1.setType(3);
                request1.setParams(paramsIp);
                requests.add(request1);
            }
            postRequest.setRequest(requests);
            postRequests.add(postRequest);
        }
        return postRequests;
    }


}
