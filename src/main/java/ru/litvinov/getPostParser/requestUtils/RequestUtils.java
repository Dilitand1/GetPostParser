package ru.litvinov.getPostParser.requestUtils;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class RequestUtils {

    public static String getRequest(String query){
        HttpURLConnection connection = null;
        StringBuilder builder = new StringBuilder();
        try{
            connection = (HttpURLConnection) new URL(query).openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setRequestMethod("GET"); //Задаем тип запроса

            connection.setConnectTimeout(1500); //задаем таймауты
            connection.setReadTimeout(1500);

            connection.setRequestProperty("Content-Language", "en-US"); //пример хеадеров

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"Windows-1251"));
                String line = "";
                while ((line = reader.readLine()) != null){
                    builder.append(line);
                }
            }
            else {
                System.out.println(connection.getResponseCode());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return builder.toString();
    }

    //post честно скопированный из тырнета
    public static String postRequest(){
        try {

            URL url = new URL("https://studytutorial.in/post.php"); // here is your URL path

            JSONObject postDataParams = new JSONObject();
            postDataParams.put("name", "abc");
            postDataParams.put("email", "abc@gmail.com");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();

            int responseCode=conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader in=new BufferedReader(new
                        InputStreamReader(
                        conn.getInputStream()));

                StringBuffer sb = new StringBuffer("");
                String line="";

                while((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }

                in.close();
                return sb.toString();

            }
            else {
                return new String("false : "+responseCode);
            }
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    public static String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }


}
