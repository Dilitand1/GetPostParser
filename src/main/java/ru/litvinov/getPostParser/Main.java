package ru.litvinov.getPostParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static ru.litvinov.getPostParser.jsonUtils.JsonUtils.jsonToObject;
import static ru.litvinov.getPostParser.jsonUtils.JsonUtils.objectToJson;

public class Main {
    public static void main(String[] args) {
        
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
