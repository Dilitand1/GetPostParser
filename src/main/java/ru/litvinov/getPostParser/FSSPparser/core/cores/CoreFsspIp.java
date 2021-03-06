package ru.litvinov.getPostParser.FSSPparser.core.cores;

import ru.litvinov.getPostParser.FSSPparser.core.cache.CacheWorkerIp;
import ru.litvinov.getPostParser.FSSPparser.core.logic.Logic;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.Params;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.PostRequest;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.Request;
import java.util.ArrayList;
import java.util.List;

public class CoreFsspIp extends CoreFssp {

    public CoreFsspIp() {

    }

    public CoreFsspIp(String token, Logic logic) {
        super(token, logic);
    }

    public CoreFsspIp(String token, Logic logic, String inputFile, String outputFailedFile, String outputSuccessFile, CacheWorkerIp cacheWorkerIp) {
        super(token, logic, inputFile, outputFailedFile, outputSuccessFile, cacheWorkerIp);
    }

    @Override
    //формируем пост запросы
    public List<PostRequest> createListObjectsForPost(List inputList) {
        List<PostRequest> postRequests = new ArrayList<>();
        //режем на части по 50 штук.
        for (int i = 0; i <= inputList.size() / 50; i++) {
            PostRequest postRequest = new PostRequest();
            postRequest.setToken(getToken());
            List<Request> requests = new ArrayList<>();
            int counter = 0;
            for (int j = i * 50; j < inputList.size() && counter != 50; j++, counter++) {
                Request request1 = new Request();
                Params paramsIp = new Params();
                paramsIp.setNumber(inputList.get(j).toString().trim());
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
