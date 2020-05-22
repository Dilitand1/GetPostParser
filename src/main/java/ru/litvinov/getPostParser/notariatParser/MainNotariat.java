package ru.litvinov.getPostParser.notariatParser;

import java.io.IOException;
import java.net.*;
import java.util.List;

public class MainNotariat {
    public static void main(String[] args) throws IOException {
        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        URL url = new URL("https://notariat.ru/ru-ru/help/probate-cases/");

        URLConnection connection = url.openConnection();
        connection.getContent();

        List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
        for (HttpCookie cookie : cookies) {
            System.out.println(cookie.getDomain());
            System.out.println(cookie);
        }
    }
}
