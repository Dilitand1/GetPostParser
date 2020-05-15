package ru.litvinov.getPostParser;

import org.json.JSONObject;
import ru.litvinov.getPostParser.utils.fileUtils.FileUtils;

import java.io.IOException;
import java.net.*;
import java.util.Iterator;

public class Main {

    private static String outputSuccessFile = "outputSuccessFile.txt";

    public static void main(String[] args) throws Exception {

        FileUtils.writeFile("kzkzkz", outputSuccessFile,true );

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
