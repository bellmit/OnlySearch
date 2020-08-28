package application.service.movie;

import application.service.feign.movie.TengxunMovieFeign;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TengxunMovieSearchService implements MovieSearchServiceInterface {

    @Autowired
    private TengxunMovieFeign tengxunMovieFeign;

    private final static String BASE_URL = "https://v.qq.com";
    private final static String PROTOCOL = "https:";
    @Override
    public void analyseBigData(Map<String, Object> map, List<Map<String, Object>> list, int pageIndex) {
        try {
            String html = tengxunMovieFeign.searchMovie(pageIndex * 60 ,60);
            if (null != html){
                Document document = Jsoup.parse(html);
                Elements oDivs = document.select("div.list_item");
                for (Element element : oDivs){
                    Map<String, Object> tmpMap = new HashMap<String, Object>();
                    tmpMap.put("title", element.selectFirst("a.figure").attr("title"));
                    tmpMap.put("imgSrc", PROTOCOL + element.selectFirst("img").attr("src"));
                    tmpMap.put("href", "/playMovieWithThreePart?playUrl=" + element.selectFirst("a.figure").attr("href"));
                    tmpMap.put("jiNumber", "");
                    tmpMap.put("subTitle", element.selectFirst("div.figure_desc").text().substring(0,6) + "...");
                    list.add(tmpMap);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
