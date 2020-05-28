package ru.litvinov.getPostParser.notariatParser;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.litvinov.getPostParser.notariatParser.config.NotariatConfigClass;
import ru.litvinov.getPostParser.notariatParser.core.Core;
import ru.litvinov.getPostParser.notariatParser.core.CoreImpl;

import java.io.*;

/**
 * В файл notariatInputFile.txt пишем клиентов в формате:
 * ФИО;ДР;Дата смерти
 * Каждый клиент в новой строке
 */
public class MainNotariat {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(NotariatConfigClass.class);
        Core core = context.getBean(CoreImpl.class);
        core.processor();
    }
}
