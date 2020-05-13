package ru.litvinov.getPostParser;

import java.io.IOException;
import java.net.*;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        String url2 = "https://api-ip.fssprus.ru/api/v1.0/search/physical?token=ZYrCH4sHnb89&region=23&firstname=Дмитрий&secondname=Андреевич&lastname=Литвинов&birthdate=19.06.1985";
        String url = "https://api-ip.fssprus.ru/api/v1.0/search/physical?token=ZYrCH4sHnb89&region=23&firstname=%D0%94%D0%BC%D0%B8%D1%82%D1%80%D0%B8%D0%B9&secondname=%D0%90%D0%BD%D0%B4%D1%80%D0%B5%D0%B5%D0%B2%D0%B8%D1%87&lastname=%D0%9B%D0%B8%D1%82%D0%B2%D0%B8%D0%BD%D0%BE%D0%B2&birthdate=19.06.1985";

        String url3 = "https://api-ip.fssprus.ru/api/v1.0/search/physical?token=" + URLEncoder.encode("ZYrCH4sHnb89&region=23&firstname=Дмитрий&secondname=Андреевич&lastname=Литвинов&birthdate=19.06.1985");

        URL url4= new URL(url2);
        URI uri = new URI(url4.getProtocol(), url4.getUserInfo(), IDN.toASCII(url4.getHost()), url4.getPort(), url4.getPath(), url4.getQuery(), url4.getRef());
        String correctEncodedURL=uri.toASCIIString();


        System.out.println(url);
        System.out.println(url3);
        System.out.println(correctEncodedURL);


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
