package application.service.tengxun;

import application.filter.SysContext;
import application.model.tengxun.PageResult;
import application.service.feign.tengxun.TenXunFeign;
import application.utils.HttpOrHttpsUrlValidatorRequestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wtl
 * @License: (C) Copyright 2020. Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020-09-12 16:32
 * @Version: 1.0
 * @Description:
 */
@Service
public class TenXunService {

    @Resource
    private TenXunFeign tenXunFeign;

    /**
     * 获取腾讯视频电视剧的接口
     *
     * @param offset   偏移量
     * @param pageSize 页大小
     * @param feature  feature
     * @param iarea    iarea
     * @param pay      pay
     * @param sort     sort
     * @param year     年份
     * @return List<PageResult>
     */
    public List<PageResult> pageList(
            String channel, int offset, int pageSize, String feature, String iarea, String pay, String sort, String year, String charge, String itype, String characteristic,String ipay,String iyear,String source,String exclusive,String plot_aspect,String language,String anime_status,String itrailer
    ) {
        List<PageResult> pageResults = new ArrayList<>();
        String htmlPart = tenXunFeign.pageList(channel, offset, pageSize, feature, iarea, pay, sort, year, charge, itype, characteristic, ipay, iyear, source, exclusive, plot_aspect, language, anime_status, itrailer);

        Document document = Jsoup.parse(htmlPart);
        Elements listItems = document.select("div.list_item");

        listItems.forEach(element -> {
            try {
                String url = element.selectFirst("a.figure").attr("href");
                String picUrl = SysContext.PROTOCOL_HTTPS + SysContext.MAO_HAO +
                        element.selectFirst("img.figure_pic").attr("src");
                String name = element.selectFirst("div.figure_detail.figure_detail_two_row a.figure_title").text();
                String jiNumber = null != element.selectFirst("div.figure_caption") ?
                        element.selectFirst("div.figure_caption").text() : "全1集";
                String introduction = null != element.selectFirst("div.figure_desc") ?
                        element.selectFirst("div.figure_desc").text() : "";
                pageResults.add(PageResult.builder()
                        .url(url)
                        .picUrl(picUrl)
                        .name(name)
                        .jiNumber(jiNumber)
                        .introduction(introduction).build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return pageResults;
    }


    /**
     * 根据指定的url进行分析出电视剧的剧集列表
     *
     * @param url 待分析的url
     * @return List<String>
     */
    public List<String> analysisPageToList(String url) {
        List<String> videoUrls = new ArrayList<>();

        String html = HttpOrHttpsUrlValidatorRequestUtils.requestHttpsGet(url, null, null, SysContext.ENCODING, SysContext.TEXT_HTML_MIME_TYPE);
        if (null == html) {
            throw new RuntimeException("无法获取指定url的页面html......");
        } else {
            Document document = Jsoup.parse(html);
            Elements elements = document.select("div.episode_filter_items span.item");
            int size = elements.size();

            String urlPrefix = url.split("\\.html")[0];
            //这里使用html，而不使用document.html()，是因为document.html()会递归计算所有的节点
            String[] videos = html.split("var LIST_INFO = ")[1]
                    .split("firstClipListVid")[0]
                    .split("\\[")[1]
                    .split("\\]")[0]
                    .split(",");

            int jiNumber = size != 0 ?
                    Integer.parseInt(elements.get(size - 1).text().split("-")[1])
                    : videos.length;

            for (int i = 0; i < jiNumber; i++) {
                videoUrls.add(urlPrefix + "/" + videos[i].replaceAll("\"", "") + ".html");
            }
            return videoUrls;
        }
    }

    /**
     * 根据指定的url进行分析出综艺节目的剧集列表
     * @param url url
     * @return List<String>
     */
    public List<Map<String,Object>> analysisVarietyShowPageToVideoList(String url){
        List<Map<String,Object>> mapList = new ArrayList<>();

        String html = HttpOrHttpsUrlValidatorRequestUtils.requestHttpsGet(url, null, null, SysContext.ENCODING, SysContext.TEXT_HTML_MIME_TYPE);
        if (null == html) {
            throw new RuntimeException("无法获取指定url的页面html......");
        } else {
            Document document = Jsoup.parse(html);
            Elements listItems = document.selectFirst("div.mod_figure.mod_figure_list.mod_figure_list_sm")
                    .select("li.list_item");

            listItems.forEach(element -> {
                Map<String,Object> map = new HashMap<>();
                String url1 = SysContext.V_QQ_COM + element.selectFirst("a").attr("href");
                String name = element.attr("data-title");
                name = SysContext.BLANK_STRING.equalsIgnoreCase(name) ? element.selectFirst("a").attr("title") : name;
                map.put("url",url1);
                map.put("name",name);
                mapList.add(map);
            });
            return mapList;
        }
    }
}
