package ru.litvinov.getPostParser.FSSPparser.core.cores;

import ru.litvinov.getPostParser.FSSPparser.core.logic.Logic;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.Params;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.PostRequest;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.Request;

import java.util.ArrayList;
import java.util.List;

public class CoreFsspFio extends CoreFssp {

    public CoreFsspFio(String token, Logic logic) {
        super(token, logic);
    }

    @Override
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
                Params params = new Params();
                String[] inputLine = inputList.get(j).toString().trim().split(";");
                request1.setType(1);
                params.setLastname(inputLine[0]);
                params.setFirstname(inputLine[1]);
                params.setSecondname(inputLine[2]);
                params.setBirthdate(inputLine[3]);
                params.setRegion(inputLine[4]);
                request1.setParams(params);
                requests.add(request1);
            }
            postRequest.setRequest(requests);
            postRequests.add(postRequest);
        }
        return postRequests;
    }
}
