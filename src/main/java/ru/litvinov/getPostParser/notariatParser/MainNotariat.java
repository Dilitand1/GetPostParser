package ru.litvinov.getPostParser.notariatParser;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.litvinov.getPostParser.notariatParser.config.NotariatConfigClass;
import ru.litvinov.getPostParser.notariatParser.core.Core;
import ru.litvinov.getPostParser.notariatParser.core.CoreImpl;
import ru.litvinov.getPostParser.utils.requestUtils.RequestUtils;

import java.io.IOException;
import java.util.List;

public class MainNotariat {
    public static void main(String[] args) throws IOException {
        List list = RequestUtils.getCookies("https://notariat.ru/ru-ru/help/probate-cases/");

        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(NotariatConfigClass.class);
        //Core core = context.getBean(CoreImpl.class);
        //String s = core.processor();
        //System.out.println(s);
    }
}
