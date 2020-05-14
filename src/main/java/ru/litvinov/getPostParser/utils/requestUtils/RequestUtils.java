package ru.litvinov.getPostParser.utils.requestUtils;

import com.sun.corba.se.impl.presentation.rmi.ExceptionHandler;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.Map;

public class RequestUtils {

    public static String getRequest(String query) {
        HttpURLConnection connection = null;
        StringBuilder builder = new StringBuilder();
        try {
            connection = (HttpURLConnection) new URL(query).openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setRequestMethod("GET"); //Задаем тип запроса

            connection.setConnectTimeout(1500); //задаем таймауты
            connection.setReadTimeout(1500);

            //connection.setRequestProperty("Content-Language", "en-US"); //пример хеадеров

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "Windows-1251"));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } else {
                System.out.println(connection.getResponseMessage());
                System.out.println(connection.getResponseCode());
                System.out.println(connection.getErrorStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    //post
    public static String postRequest(String myUrl, String body, Map<String, String> headers) {
        try {
            URL url = new URL(myUrl); // here is your URL path
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //открываем коннект
            conn.setReadTimeout(15000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //наполняем хэдеры
            //пример создания хэдеров:
            //conn.setRequestProperty("Content-Type", "application/json; utf-8");
            //conn.setRequestProperty("Accept", "application/json");
            if (!headers.isEmpty()) {
                Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            //грузим тело запроса
            try (OutputStream os = conn.getOutputStream();
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));) {
                //byte[] input = body.getBytes("utf-8");
                //os.write(input, 0, input.length);
                writer.write(body);
                writer.flush();
            }

            //получаем ответ
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));) {
                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                    }
                    return sb.toString();
                }
            } else {
                throw new Exception();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encodeString(String s) throws MalformedURLException, URISyntaxException {
        URL url4 = new URL(s);
        URI uri = new URI(url4.getProtocol(), url4.getUserInfo(), IDN.toASCII(url4.getHost()), url4.getPort(), url4.getPath(), url4.getQuery(), url4.getRef());
        return uri.toASCIIString();
    }

//
//    public static String getPostDataString(JSONObject params) throws Exception {

//    JSONObject postDataParams = new JSONObject();
    //postDataParams.put("name", "abc");
    //postDataParams.put("email", "abc@gmail.com");


//
//        StringBuilder result = new StringBuilder();
//        boolean first = true;
//
//        Iterator<String> itr = params.keys();
//
//        while(itr.hasNext()){
//
//            String key= itr.next();
//            Object value = params.get(key);
//
//            if (first)
//                first = false;
//            else
//                result.append("&");
//
//            result.append(URLEncoder.encode(key, "UTF-8"));
//            result.append("=");
//            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
//
//        }
//        return result.toString();
//    }


}
