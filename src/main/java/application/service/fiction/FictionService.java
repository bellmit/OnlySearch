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
        Elements elements = document.select("div.store_collist div.bookbox");
        for (Element element : elements) {
            Element bookImg = element.selectFirst("div.bookimg");
            Element bookInfo = element.selectFirst("div.bookinfo");
            Element bookLink = element.selectFirst("div.bookilnk");
            Element bookIntro = element.selectFirst("div.bookintro");
            fictions.add(new Fiction(
                    bookImg.selectFirst("a > img").attr("src"),
                    bookInfo.selectFirst("div.bookname > a").text(),
                    bookImg.selectFirst("a").attr("href"),
                    bookLink.selectFirst("a").text(),
                    bookLink.selectFirst("span:nth-of-type(2)").text(),
                    bookIntro.text(),
                    bookLink.selectFirst("span:nth-of-type(1)").text()
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
        Elements volumes = document.select("div.volume-list div.volume");
        Elements chapterList = document.select("div.volume-list ul.chapter-list");
        for (int i = 0; i < volumes.size(); i++) {
            Element volume = volumes.get(i);
            Element chapter = chapterList.get(i);
            Map<String, Object> map = new HashMap<>(2);
            list.add(map);

            map.put("volume", volume.text().replaceAll("\\[分卷阅读] ", ""));
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

    public Map<String,Object> getChapterCatalog(String id, String chapterId) {
        Map<String,Object> map = new HashMap<>(5);
        String html = fictionFeign.getChapter(id, chapterId);
        Document document = Jsoup.parse(html);
        String title = document.selectFirst("div.title div.title_txtbox").text();
        String content = document.selectFirst("div.content").html();
        String author = document.selectFirst("div.bookinfo > a").text();
        String wordCount = document.selectFirst("div.bookinfo span:nth-of-type(1)").text();
        String updateTime = document.selectFirst("div.bookinfo span:nth-of-type(2)").text();
        map.put("title",title);
        map.put("content",content);
        map.put("author",author);
        map.put("wordCount",wordCount);
        map.put("updateTime",updateTime);
        return map;
    }
}
