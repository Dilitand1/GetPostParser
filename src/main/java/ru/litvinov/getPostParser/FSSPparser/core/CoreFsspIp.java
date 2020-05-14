package ru.litvinov.getPostParser.FSSPparser.core;

import ru.litvinov.getPostParser.FSSPparser.models.getResponse.GetResponse;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.ParamsIp;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.PostRequest;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.Request;
import ru.litvinov.getPostParser.utils.jsonUtils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class CoreFsspIp extends CoreFssp {

    public CoreFsspIp() {
    }

    public CoreFsspIp(String token, Logic logic) {
        super(token, logic);
    }

    public CoreFsspIp(String token, Logic logic, String inputFile, String casheFile, String outputFailedFile, String outputSuccessFile, CacheWorkerIp cacheWorkerIp) {
        super(token, logic, inputFile, casheFile, outputFailedFile, outputSuccessFile, cacheWorkerIp);
    }

    @Override
    //обработчик
    public GetResponse sendPostProcessor(PostRequest postRequest) throws Exception {
        String requestString = JsonUtils.objectToJson(postRequest);
        GetResponse getResponse = null;
        try {
            //отправляем запрос
            getResponse = (GetResponse) getLogic().sendPost(requestString);
            //пишем в кэш
            getCacheWorkerIp().writePostRequestResultIp(postRequest,getResponse);
            Thread.sleep(5000); //спим 5 секунд
        } catch (Exception e) {
            //если косяк то обрабатываем ошибку
            System.out.println(e.getMessage());
            if (e.getMessage().contains("Too Many")){
                //Если много запросов то ждем 30 сек
                System.out.println("Много запросов спим 1 минуту");
                Thread.sleep(60000);
                getResponse = sendPostProcessor(postRequest);
            } else {
                throw e;
            }
        }
        //сохраняем кэш
        getCacheWorkerIp().saveCache();
        return getResponse;
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
