package application.service.tv;

import application.filter.SysContext;
import application.model.tengxun.PageResult;
import application.service.feign.tv.TenXunTvFeign;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wtl
 * @License: (C) Copyright 2020, wtl Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020-09-16 6:23
 * @Version: 1.0
 * @Description:
 */
@Service
public class TenXunTVPlaySearchService implements TVPlaySearchServiceInterface{

    @Resource
    private TenXunTvFeign tenXunTvFeign;

    @Override
    public void analyseBigData(Map<String, Object> map, List<Map<String, Object>> list, int pageIndex) {
        String htmlPart = tenXunTvFeign.searchTv((pageIndex - 1) * 30, 30);
        Document document = Jsoup.parse(htmlPart);
        Elements listItems = document.select("div.list_item");

        listItems.forEach(element -> {
            try {
                Map<String,Object> tmpMap = new HashMap<>(6);
                String href = element.selectFirst("a.figure").attr("href");
                String imgSrc = SysContext.PROTOCOL_HTTPS + SysContext.MAO_HAO +
                        element.selectFirst("img.figure_pic").attr("src");
                String title = element.selectFirst("div.figure_detail.figure_detail_two_row a.figure_title").text();
                String jiNumber = null != element.selectFirst("div.figure_caption") ?
                        element.selectFirst("div.figure_caption").text() : "全1集";
                String subTitle = null != element.selectFirst("div.figure_desc") ?
                        element.selectFirst("div.figure_desc").text() : "";
                String platform = "tengxun";
                tmpMap.put("title",title);
                tmpMap.put("imgSrc",imgSrc);
                tmpMap.put("href",href);
                tmpMap.put("jiNumber",jiNumber);
                tmpMap.put("subTitle",subTitle);
                tmpMap.put("platform",platform);
                list.add(tmpMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
