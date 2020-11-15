package application.service.tengxun_manhua;

import application.filter.SysContext;
import application.service.feign.tengxun_manhua.TengXunManHuaFeign;
import application.utils.UUID;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.host.Window;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: wtl
 * @License: (C) Copyright 2020, wtl Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020/11/15 4:07
 * @Version: 1.0
 * @Description: 腾讯漫画的selenium的本话爬取
 */
@Service
public class TengXunManHuaService {

    private static String templateHtml = null;

    static {
        try {
            BufferedInputStream bufferedInputStream = (BufferedInputStream) TengXunManHuaService.class.getResourceAsStream("/static/htmls/腾讯漫画模板.html");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int length = -1;
            byte [] buffer = new byte[10240];
            while((length = bufferedInputStream.read(buffer))!=-1){
                byteArrayOutputStream.write(buffer,0,length);
            }
            templateHtml = new String(byteArrayOutputStream.toByteArray());
            bufferedInputStream.close();
            byteArrayOutputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Resource
    private TengXunManHuaFeign tengXunManHuaFeign;


    /**
     * 计算出章节获取图片所需要的window.nonce、window.DATA
     * @return Map<String,Object>
     */
    public Map<String,Object> computeNonceAndData() throws Exception {
        String tengXunManHuaChapterHtml = tengXunManHuaFeign.getTengXunManHuaChapterHtml();
        Document document = Jsoup.parse(tengXunManHuaChapterHtml);
        Elements elements = document.select("script");
        List<Element> nonceElementList = Stream.of(elements.toArray(new Element[0])).filter(element -> {
            return element.html().contains("window[\"");
        }).collect(Collectors.toList());

        List<Element> dataElementList = Stream.of(elements.toArray(new Element[0])).filter(element -> {
            return element.html().contains("var DATA = ");
        }).collect(Collectors.toList());
        String nonceScriptStr = nonceElementList.get(nonceElementList.size() - 1).html();
        String dataScriptStr = dataElementList.get(dataElementList.size() - 1).html();
        String scriptStr = nonceScriptStr + dataScriptStr + SysContext.EXECUTE_SCRIPT;
        WebClient webClient = new WebClient();

        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

        /*
         * 写一个html文件
         */

        templateHtml = templateHtml.replaceAll("<script id=\"mineScript\" type=\"text/javascript\">", "<script id=\"mineScript\" type=\"text/javascript\">\n" + scriptStr);
        Document templateDocument = Jsoup.parse(templateHtml);
        String templateHtml = templateDocument.html();
        String templateFileName = SysContext.TEMPLATE_FILE_PREFIX_PATH + UUID.getUUID();
        FileOutputStream fileOutputStream = new FileOutputStream(templateFileName);
        fileOutputStream.write(templateHtml.getBytes());
        fileOutputStream.close();

        webClient.getPage(new URL("file://" + templateFileName));

        Window window = webClient.getCurrentWindow().getScriptableObject();
        //String nonce = String.valueOf(window.get("nonce"));
        String data = String.valueOf(window.get("T"));

        //System.out.println(nonce);
        System.out.println(data);
        File templateFile = new File(templateFileName);
        while(templateFile.exists()){
            templateFile.delete();
        }

        String chapterData = new String(Base64.getDecoder().decode(data));

        System.out.println(chapterData);
        return null;
    }
}
