package ru.litvinov.getPostParser.notariatParser;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.litvinov.getPostParser.notariatParser.config.NotariatConfigClass;
import ru.litvinov.getPostParser.notariatParser.core.Core;
import ru.litvinov.getPostParser.notariatParser.core.CoreImpl;
import ru.litvinov.getPostParser.notariatParser.models.result.GetResult;
import ru.litvinov.getPostParser.utils.fileUtils.FileUtils;
import ru.litvinov.getPostParser.utils.jsonUtils.JsonUtils;

import java.io.*;

public class MainNotariat {
    public static void main(String[] args) throws IOException {
//{"name":"ИВАНОВ ИВАН ИВАНОВИЧ","birth_date":"null","death_date":"null"}

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(NotariatConfigClass.class);
        Core core = context.getBean(CoreImpl.class);
        core.processor();
    }

}
