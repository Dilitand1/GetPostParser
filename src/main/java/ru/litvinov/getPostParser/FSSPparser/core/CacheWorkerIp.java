package ru.litvinov.getPostParser.FSSPparser.core;

import ru.litvinov.getPostParser.FSSPparser.models.getResponse.GetResponse;
import ru.litvinov.getPostParser.FSSPparser.models.getResponse.Response_;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.PostRequest;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.Request;
import ru.litvinov.getPostParser.utils.fileUtils.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CacheWorkerIp {

    private String casheFile = "cacheFile.txt";
    private Map<String, GetResponse> cacheMap = new HashMap<>();

    public CacheWorkerIp(String casheFile) {
        this.casheFile = casheFile;
    }

    public void init() {
        try {
            //BasicFileAttributes basicFileAttributes = Files.readAttributes(Paths.get(casheFile),BasicFileAttributes.class);
            BasicFileAttributes basicFileAttributes = Files.readAttributes(Paths.get("cacheFile.txt"), BasicFileAttributes.class);
            Date creationDate = new Date(basicFileAttributes.creationTime().to(TimeUnit.MILLISECONDS));
            Calendar yeasterday = Calendar.getInstance();
            yeasterday.add(Calendar.DATE,-1);
            if (creationDate.getTime() < yeasterday.getTime().getTime()) {
                System.out.println("Кэш устарел");
                throw new IOException("Кэш устарел");
            }

            List<String> cacheList = Files.readAllLines(Paths.get(casheFile));
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
            printCache();
            System.out.println("Кэш загружен");

        } catch (IOException e) {
            System.out.println("Кэш не найден");
            //e.printStackTrace();
        }
    }

    public void writePostRequestResultIp(PostRequest postRequest, GetResponse getResponse) {
        List<Request> requests = postRequest.getRequest();

        for (int i = 0; i < requests.size(); i++) {
            String requestIp = requests.get(i).getParams().getNumber();
            if (cacheMap.get(requestIp) == null || !cacheMap.get(requestIp).getStatus().equals("success")){
                cacheMap.put(requestIp,getResponse);
                //System.out.println("sended:" + requestIp + "~" + getResponse);
            }
        }
    }

    public void addElement(String s,GetResponse getResponse) {
        cacheMap.put(s,getResponse);
    }

    public void removeFromCache(String s) {
        cacheMap.remove(s);
    }

    public void saveCache() {
        FileUtils.writeFile(cacheMap, casheFile);
    }

    public void printCache(){
        for(Map.Entry<String,GetResponse> entry : cacheMap.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue().toString());
        }
    }
}
