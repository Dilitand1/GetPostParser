package ru.litvinov.getPostParser.FSSPparser.core;

import ru.litvinov.getPostParser.FSSPparser.models.getResponse.GetResponse;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.PostRequest;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.Request;
import ru.litvinov.getPostParser.utils.fileUtils.FileUtils;
import ru.litvinov.getPostParser.utils.serialize.SerializationImpl;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CacheWorkerIp extends CacheWork implements Serializable {

    private Map<String, GetResponse> cacheMap = new HashMap<>();

    public CacheWorkerIp() {
        super.casheFile = "cacheFileIp.dat";
        super.cacheMap = this.cacheMap;
    }

    public CacheWorkerIp(String casheFile) {
        super(casheFile);
    }

    public void init() {
        try {
            //Проверяем устарел ли кэш, срок установим 22 часа
            BasicFileAttributes basicFileAttributes = Files.readAttributes(Paths.get(casheFile), BasicFileAttributes.class);
            Date creationDate = new Date(basicFileAttributes.creationTime().to(TimeUnit.MILLISECONDS));
            //Calendar yeasterday = Calendar.getInstance();
            // yeasterday.add(Calendar.DATE,-1);
            if (creationDate.getTime() < (new Date().getTime() - (22 * 60 * 60 * 1000)) /*yeasterday.getTime().getTime()*/) {
                System.out.println("Кэш устарел");
                throw new IOException("Кэш устарел");
            }

            //Реализация без сериализации
/*            List<String> cacheList = Files.readAllLines(Paths.get(casheFile));
            for (String s : cacheList) {
                String[] splitString = s.split("~");
                GetResponse getResponse = new GetResponse();
                Response_ response_ = new Response_();
                response_.setTask(splitString[1]);
                getResponse.setResponse(response_);
                getResponse.setStatus(splitString[2]);
                getResponse.setCode(Integer.parseInt(splitString[3]));
                getResponse.setException(splitString[4]);
                cacheMap.put(splitString[0], getResponse);
                }
*/
            CacheWorkerIp cacheWorkerIp = (CacheWorkerIp) SerializationImpl.loadObject(casheFile);
            this.cacheMap.putAll(cacheWorkerIp.cacheMap);
            printCache();
            System.out.println("Кэш загружен");

        } catch (IOException e) {
            System.out.println("Кэш не найден");
            //e.printStackTrace();
        }
    }

    @Override
    public void writePostRequestResult(PostRequest postRequest, GetResponse getResponse) {
        List<Request> requests = postRequest.getRequest();

        for (int i = 0; i < requests.size(); i++) {
            String requestIp = requests.get(i).getParams().getNumber();
            if (cacheMap.get(requestIp) == null || !cacheMap.get(requestIp).getStatus().equals("success")) {
                cacheMap.put(requestIp, getResponse);
                //System.out.println("sended:" + requestIp + "~" + getResponse);
            }
        }
    }

    @Override
    public void saveCache() {
        //FileUtils.writeFile(cacheMap, casheFile);
        SerializationImpl.saveObject(this,casheFile);
    }

    @Override
    public void saveCasheToFile(String outputFile) {
        FileUtils.removeFile(outputFile);
        for (Map.Entry<String,GetResponse> entry : cacheMap.entrySet()) {
            FileUtils.writeFile(entry.getKey() + "~" + entry.getValue().getResponse().getTask()
                    + "~" + entry.getValue().getStatus() + "~" + entry.getValue().getCode()
                    + "~" + entry.getValue().getException() + "\n", outputFile, true);
        }
    }
}
