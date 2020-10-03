package application.service.fiction;

import application.model.fiction.Chapter;
import application.model.fiction.Fiction;
import application.mybatis.mappers.FictionMapper;
import application.service.feign.fiction.FictionFeign;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: 小说service层
 * @date 2020-03-21 16:59:03
 */
@Service
public class FictionService {

    @Resource
    private FictionFeign fictionFeign;

    @Resource
    private FictionMapper fictionMapper;

    public List<Fiction> listFictions(int pageIndex) {
        List<Fiction> fictions = new ArrayList<>(50);
        String html = fictionFeign.listFictions(pageIndex);
        Document document = Jsoup.parse(html);
        Elements elements = document.select("ul.filter-ret.clear li.fl.clear");
        for (Element element : elements) {
            Element bookImg = element.selectFirst("img");
            Element bookInfo = element.selectFirst("div.info");
            Element bookLink = element.selectFirst("a.cover");
            Element bookIntro = element.selectFirst("div.info > div.d2");

            fictions.add(new Fiction(
                    bookImg.attr("src"),
                    bookInfo.selectFirst("h3 > a").text(),
                    bookLink.attr("href"),
                    bookInfo.selectFirst("div.d1").text().split(" ")[1],
                    bookInfo.selectFirst("div.d1 > a").text(),
                    bookIntro.text(),
                    Fiction.STATUS_SERIAL
            ));
        }
        return fictions;
    }

    public List<Fiction> queryByKeyword(String keyword, int offset, int size) {
        return fictionMapper.queryByKeyword(keyword, offset, size);
    }

    public List<Map<String, Object>> showChapter(String id) {
        List<Map<String, Object>> list = new ArrayList<>();
        String html = fictionFeign.showChapter(id);
        Document document = Jsoup.parse(html);
        Elements chapterList = document.select("div.chapter-box ul.chapter li");
        for (int i = 0; i < chapterList.size(); i++) {
            Element chapter = chapterList.get(i);
            Map<String, Object> map = new HashMap<>(2);
            list.add(map);

            Elements chapterElements = chapter.select("li");
            List<Chapter> chapters = new ArrayList<>();
            map.put("chapters", chapters);
            for (Element chapterElement : chapterElements) {
                Element element = chapterElement.selectFirst("a");
                chapters.add(new Chapter(
                        element.attr("href"),
                        element.text()
                ));
            }
        }
        return list;
    }

    public Map<String, Object> getChapterCatalog(String id, String chapterId) {
        Map<String, Object> map = new HashMap<>(5);
        String html = fictionFeign.getChapter(id, chapterId);
        Document document = Jsoup.parse(html);
        Element element = document.selectFirst("div.paper-box.paper-article");
        String title = element.selectFirst("h1").text();

        String content = element.selectFirst("div#contentWp").html();
        Element infoElement = element.selectFirst("div.info");
        String author = infoElement.text().split(" ")[1].replace("作者：", "");
        String wordCount = infoElement.text().split(" ")[2].replace("字数：", "");
        String updateTime = infoElement.text().split(" ")[3].replace("更新时间：","");
        map.put("title", title);
        map.put("content", content);
        map.put("author", author);
        map.put("wordCount", wordCount);
        map.put("updateTime", updateTime);
        return map;
    }
}
