package ru.litvinov.getPostParser.notariatParser;

import org.jsoup.Jsoup;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.litvinov.getPostParser.notariatParser.config.NotariatConfigClass;
import ru.litvinov.getPostParser.notariatParser.core.Core;
import ru.litvinov.getPostParser.notariatParser.core.CoreImpl;
import ru.litvinov.getPostParser.notariatParser.models.Client;
import ru.litvinov.getPostParser.utils.fileUtils.FileUtils;
import ru.litvinov.getPostParser.utils.jsonUtils.JsonUtils;
import ru.litvinov.getPostParser.utils.requestUtils.RequestUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class MainNotariat {
    public static void main(String[] args) throws IOException {
        //List list = RequestUtils.getCookies("https://notariat.ru");
        //convert("tmp.txt","data.txt",null,"Windows-1251");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(NotariatConfigClass.class);
        Core core = context.getBean(CoreImpl.class);
        String s = core.processor();
        System.out.println(s);

         //        Client client = new Client();
//        client.setName("ШИРЯЕВА СОНИЯ АРИФОВНА");
//        client.setBirth_date("19610831");
//        client.setDeath_date("20180313");
//        String body = JsonUtils.objectToJson(client);
//        System.out.println(body);

        /*
        String url = "https://notariat.ru//";//"https://www.avito.ru";
        Map<String, String> cookies = Jsoup.connect(url).execute().cookies();
        System.out.println(cookies);


         */
    }

    public static void convert(
            String infile, //input file name, if null reads from console/stdin
            String outfile, //output file name, if null writes to console/stdout
            String from,   //encoding of input file (e.g. UTF-8/windows-1251, etc)
            String to)     //encoding of output file (e.g. UTF-8/windows-1251, etc)
            throws IOException, UnsupportedEncodingException
    {
        // set up byte streams
        InputStream in;
        if(infile != null)
            in=new FileInputStream(infile);
        else
            in=System.in;
        OutputStream out;
        if(outfile != null)
            out=new FileOutputStream(outfile);
        else
            out=System.out;

        // Use default encoding if no encoding is specified.
        if(from == null) from=System.getProperty("file.encoding");
        if(to == null) to=System.getProperty("file.encoding");

        // Set up character stream
        Reader r=new BufferedReader(new InputStreamReader(in, from));
        Writer w=new BufferedWriter(new OutputStreamWriter(out, to));

        // Copy characters from input to output.  The InputStreamReader
        // converts from the input encoding to Unicode,, and the OutputStreamWriter
        // converts from Unicode to the output encoding.  Characters that cannot be
        // represented in the output encoding are output as '?'
        char[] buffer=new char[4096];
        int len;
        while((len=r.read(buffer)) != -1)
            w.write(buffer, 0, len);
        r.close();
        w.flush();
        w.close();
    }
}
