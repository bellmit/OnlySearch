package application.service.tengxun_manhua;

import application.filter.SysContext;
import application.service.feign.tengxun_manhua.TengXunManHuaFeign;
import application.utils.UUID;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.javascript.host.Window;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.*;
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

    /**
     * 域名
     */
    public static final String DOMAIN_NAME = "https://ac.qq.com";

    static {
        try {
            File templateDir = new File(SysContext.TEMPLATE_FILE_PREFIX_PATH);
            if (!templateDir.exists()){
                templateDir.mkdirs();
            }

            BufferedInputStream bufferedInputStream = (BufferedInputStream) TengXunManHuaService.class.getResourceAsStream("/static/htmls/tengxun_manhua/腾讯漫画模板.html");
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
     * 分页获取全部漫画的列表
     * @param pageIndex 页码
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getTengXunManHuaList(String classify,int pageIndex){
        List<Map<String,Object>> tengXunManHuaList = new ArrayList<>();
        String html = null;
        if (SysContext.STRING_ALL.equals(classify)){
            html = tengXunManHuaFeign.getTengXunManHuaAllList(pageIndex);
        }
        else{
            html = tengXunManHuaFeign.getTengXunManHuaClassifyList(classify,pageIndex);
        }

        Document document = Jsoup.parse(html);

        Elements elements = document.select("ul.ret-search-list li.ret-search-item");
        elements.forEach(element -> {
            Element aElement = element.selectFirst("div.ret-works-cover > a.mod-cover-list-thumb.mod-cover-effect.ui-db");
            Element spanElement = element.selectFirst("p.mod-cover-list-updata span.mod-cover-list-text");
            Element pElement = element.selectFirst("div.ret-works-info > p.ret-works-author");
            Elements tagsElements = element.select("div.ret-works-info > p.ret-works-tags > span");
            Elements popularityElement = element.select("div.ret-works-info > span");
            Element descriptionElement = element.selectFirst("div.ret-works-info > p.ret-works-decs");
            String title = aElement.attr("title");
            String manHuaMainUrl = DOMAIN_NAME + aElement.attr("href");
            String manHuaMainPic = aElement.selectFirst("img").attr("data-original");
            String updateTo = spanElement.text();
            String author = pElement.text();
            Map<String,Object> tags = new HashMap<>(tagsElements.size());
            tagsElements.forEach(ele->{
                if (!ele.text().contains("人气")){
                    tags.put(ele.text(), DOMAIN_NAME + ele.attr("href"));
                }
            });
            String popularity = popularityElement.text();
            String description = descriptionElement.text();

            Map<String,Object> map = new HashMap<>(8);
            map.put("title",title);
            map.put("manHuaMainUrl",manHuaMainUrl);
            map.put("manHuaMainPic",manHuaMainPic);
            map.put("updateTo",updateTo);
            map.put("author",author);
            map.put("tags",tags);
            map.put("popularity",popularity);
            map.put("description",description);
            tengXunManHuaList.add(map);
        });

        return tengXunManHuaList;
    }


    /**
     * 获取漫画的详情根据漫画id
     * @param id id唯一标识
     * @return Map<String,Object>
     */
    public Map<String,Object> getTengXunManHuaInfoById(String id){
        Map<String,Object> tengXunManHuaInfoMap = new HashMap<>();
        String html = tengXunManHuaFeign.getTengXunManHuaInfoById(id);
        Document document = Jsoup.parse(html);

        Element bgElement = document.selectFirst("div#special_bg");
        Element titleElement = bgElement.selectFirst("div.works-intro-text strong");
        Elements spanElements = bgElement.select("div.works-intro-text > p.works-intro-digi > em > span");
        Element descriptionElement = bgElement.selectFirst("div.works-intro-text p.works-intro-short.ui-text-gray9");
        Elements chapterListElements = bgElement.select("div.works-chapter-list-wr > ol a");
        String title = titleElement.text();
        String score = bgElement.selectFirst("div.works-score > p.ui-left").html().trim();
        String author = bgElement.selectFirst("div.works-intro-text > p.works-intro-digi > span.first").text()
                .replaceAll("作者：",SysContext.BLANK_STRING);
        //人气
        String popularity = spanElements.get(0).text().replaceAll("人气：", SysContext.BLANK_STRING);
        //收藏数
        String collectionNumber = spanElements.get(1).text().replaceAll("收藏数：", SysContext.BLANK_STRING);
        String description = descriptionElement.text().trim();
        List<Map<String,Object>> allChapter = new ArrayList<>();

        chapterListElements.forEach(element -> {
            Map<String,Object> chapterInfoMap = new TreeMap<>();
            chapterInfoMap.put(DOMAIN_NAME + element.attr("href"),element.attr("title"));
            allChapter.add(chapterInfoMap);
        });

        tengXunManHuaInfoMap.put("title",title);
        tengXunManHuaInfoMap.put("score",score);
        tengXunManHuaInfoMap.put("author",author);
        tengXunManHuaInfoMap.put("popularity",popularity);
        tengXunManHuaInfoMap.put("collectionNumber",collectionNumber);
        tengXunManHuaInfoMap.put("description",description);
        tengXunManHuaInfoMap.put("allChapter",allChapter);
        return tengXunManHuaInfoMap;
    }

    /**
     * 计算出章节获取图片所需要的window.nonce、window.DATA
     * @param id 唯一标识
     * @param cid cid
     * @return Map<String,Object>
     */
    public String computeNonceAndData(String id,String cid) throws Exception {
        String tengXunManHuaChapterHtml = tengXunManHuaFeign.getTengXunManHuaChapterHtml(id, cid);
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

        String temHtml = templateHtml.replace("<script id=\"mineScript\" type=\"text/javascript\">", "<script id=\"mineScript\" type=\"text/javascript\">\n" + scriptStr);
        Document templateDocument = Jsoup.parse(temHtml);
        temHtml = templateDocument.html();
        String templateFileName = SysContext.TEMPLATE_FILE_PREFIX_PATH + UUID.getUUID();
        FileOutputStream fileOutputStream = new FileOutputStream(templateFileName);
        fileOutputStream.write(temHtml.getBytes());
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

        webClient.close();
        return new String(Base64.getDecoder().decode(data));
    }
}
