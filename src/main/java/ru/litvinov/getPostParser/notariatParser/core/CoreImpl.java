package ru.litvinov.getPostParser.notariatParser.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.litvinov.getPostParser.notariatParser.models.Client;
import ru.litvinov.getPostParser.utils.jsonUtils.JsonUtils;
import ru.litvinov.getPostParser.utils.requestUtils.RequestUtils;

import javax.annotation.PostConstruct;
import java.net.HttpCookie;
import java.util.List;
import java.util.Map;

@Component
public class CoreImpl implements Core {

    @Value("https://notariat.ru/api/probate-cases/eis-proxy")
    private String url;
    @Autowired
    @Qualifier("headers")
    private Map headers;

    public CoreImpl(){};

    @PostConstruct
    public void init(){
        //Загружаем куки и дописываем хедеры при иницииализации

        List<HttpCookie> cookies = RequestUtils.getCookies(url);
        StringBuilder builder = new StringBuilder();
        if (!cookies.isEmpty()) {
            for (HttpCookie cookie : cookies){
                String tmp = cookie.toString();
                if (tmp.startsWith("fnc_csrftoken")){
                    System.out.println(tmp);
                    headers.put("X-CSRFToken",tmp.split("=")[1]);
                }
                builder.append(tmp).append("; ");
            }
            String cookie = builder.toString();
            headers.put("Cookie",cookie.substring(0,cookie.length()-2));
        }
    }

    public String processor(){
        Client client = new Client();
        client.setName("ШИРЯЕВА СОНИЯ АРИФОВНА");
        client.setBirth_date("19610831");
        client.setDeath_date("20180313");
        String body = JsonUtils.objectToJson(client);
        headers.put("Content-Length",String.valueOf(body.length()));
        return sendPost(JsonUtils.objectToJson(client));
    }

    @Override
    public String sendPost(String body) {
        String s = "";
        try {
            s = RequestUtils.postRequest(url, body, headers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map getHeaders() {
        return headers;
    }

    public void setHeaders(Map headers) {
        this.headers = headers;
    }
}
