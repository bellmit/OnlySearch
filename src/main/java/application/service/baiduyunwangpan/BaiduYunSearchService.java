package application.service.baiduyunwangpan;

import application.service.feign.baiduyun.BaiDuFeign;
import application.utils.HttpOrHttpsUrlValidatorRequestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wtl
 */
@Service
public class BaiduYunSearchService {

    private static final String HOST_URL = "http://baiduyun.6miu.com";

    @Resource
    private BaiDuFeign baiDuFeign;

    public List<Map<String,Object>> getSearchPagingBaiduYunSources(String keyword,
                                                    String pageIndex){
        List<Map<String,Object>> baiDuYunWangPans = new ArrayList<>();
        try {
            String html = baiDuFeign.getSearchPagingBaiduYunSources(URLEncoder.encode(keyword, "utf-8"), pageIndex);
            Document document = Jsoup.parse(html);
            Elements elements = document.select("div.row div.col-md-8 div.panel.panel-default");
            elements.forEach(element -> {
                Map<String,Object> map = new HashMap<>();
                Element h5Ele = element.selectFirst("h5.item-title > a");
                AtomicReference<String> resourceUrl = new AtomicReference<>(HOST_URL + h5Ele.attr("href"));
                String resourceName = h5Ele.html();
                Elements tableSpanElements = element.select("table tr td span b");
                String resourceTime = tableSpanElements.get(0).text();
                String resourceSize = tableSpanElements.get(1).text();
                String href = Jsoup.parse(
                        Objects.requireNonNull(HttpOrHttpsUrlValidatorRequestUtils.requestHttpsGet(resourceUrl.get(), null, null, "utf-8", MediaType.TEXT_HTML_VALUE))
                ).selectFirst("div.downbutton.center a").attr("href");
                System.out.println(href);
                Document doc = Jsoup.parse(Objects.requireNonNull(HttpOrHttpsUrlValidatorRequestUtils.requestHttpsGet(href, null, null, "utf-8", MediaType.TEXT_HTML_VALUE)));
                Elements elements1 = doc.select("p");
                elements1.forEach(element1 -> {
                    if (element1.html().contains("您可以选择：")){
                        Element ele = element1.selectFirst("a");
                        resourceUrl.set(ele.attr("href"));
                    }
                });

                map.put("resourceUrl",resourceUrl.get());
                map.put("resourceName",resourceName);
                map.put("resourceTime",resourceTime);
                map.put("resourceSize",resourceSize);

                baiDuYunWangPans.add(map);
            });

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return baiDuYunWangPans;
    }
}
