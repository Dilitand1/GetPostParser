package ru.litvinov.getPostParser;

import org.json.JSONObject;

import java.io.IOException;
import java.net.*;
import java.util.Iterator;

public class Main {

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

    public static void main(String[] args) throws Exception {

        int x = 50%50;
        System.out.println(x);

        /*
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());
        */
    }

    private static class TestClass
    {
        private int id;
        private String name;

        public TestClass(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public TestClass() {
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

    }
}
