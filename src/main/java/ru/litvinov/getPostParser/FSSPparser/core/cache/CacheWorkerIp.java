package ru.litvinov.getPostParser.FSSPparser.core.cache;

import ru.litvinov.getPostParser.FSSPparser.models.getResponse.GetResponse;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.Params;
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

    //private Map<Params, GetResponse> cacheMap = new LinkedHashMap<>();

    public CacheWorkerIp() {
        super.casheFile = "cacheFileIp.dat";
        super.cacheMap = this.cacheMap;
    }

    public CacheWorkerIp(String casheFile) {
        super(casheFile);
    }

    public void init() {
        if (!isLoaded) {
            synchronized (CacheWorkerIp.class) {
                if (!isLoaded) {
                    try {
                        //   throw new IOException("Кэш временно отключен из за проблем с реализацией.");
                        BasicFileAttributes basicFileAttributes = Files.readAttributes(Paths.get(casheFile), BasicFileAttributes.class);
                        Date creationDate = new Date(basicFileAttributes.creationTime().to(TimeUnit.MILLISECONDS));
                        if (creationDate.getTime() < (new Date().getTime() - (22 * 60 * 60 * 1000))) {
                            System.out.println("Кэш устарел");
                            FileUtils.renameFile(casheFile, "old" + casheFile);
                            FileUtils.removeFile(casheFile);
                            throw new IOException("Кэш устарел");
                        }
                        CacheWorkerIp cacheWorkerIp = (CacheWorkerIp) SerializationImpl.loadObject(casheFile);
                        this.cacheMap.putAll(cacheWorkerIp.cacheMap);
                        //printCache();
                        System.out.println("Кэш загружен");

                    } catch (IOException e) {
                        System.out.println("Кэш не найден");
                    }
                }
            }
        }
    }

    @Override
    public synchronized void writePostRequestResult(PostRequest postRequest, GetResponse getResponse) {
        List<Request> requests = postRequest.getRequest();
        for (int i = 0; i < requests.size(); i++) {
            //String request = requests.get(i).getParams().toString();
            if (cacheMap.get(requests.get(i).getParams()) == null || !cacheMap.get(requests.get(i).getParams()).getStatus().equals("success")) {
                cacheMap.put(requests.get(i).getParams(), getResponse);
            }
        }
    }

    @Override
    public synchronized void saveCache() {
        //FileUtils.writeFile(cacheMap, casheFile);
        SerializationImpl.saveObject(this, casheFile);
    }

    @Override
    public synchronized void saveCasheToFile(String outputFile) {
        FileUtils.removeFile(outputFile);
        for (Map.Entry<Params, GetResponse> entry : cacheMap.entrySet()) {
            FileUtils.writeFile(entry.getValue().getResponse().getTask()
                    + "~" + entry.getValue().getStatus() + "~" + entry.getValue().getCode()
                    + "~" + entry.getValue().getException()
                    + "~" + entry.getKey().toString() + "\n", outputFile, true);
        }
    }
}
