package application.service.movie;

import application.filter.SysContext;
import application.model.tengxun.PageParams;
import application.model.tengxun.PageResult;
import application.service.feign.tengxun.TenXunFeign;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TengxunMovieSearchService implements MovieSearchServiceInterface {

    @Resource
    private TenXunFeign tenXunFeign;

    private final static String BASE_URL = "https://v.qq.com";
    private final static String PROTOCOL = "https:";
    @Override
    public void analyseBigData(Map<String, Object> map, List<Map<String, Object>> list, int pageIndex) {
        try {
            PageParams pageParams = new PageParams();
            pageParams.setChannel("movie");
            String htmlPart = tenXunFeign.pageList(pageParams.getChannel(),(pageIndex -1) * 30,30,pageParams.getFeature(),pageParams.getIarea(),pageParams.getPay(),pageParams.getSort(),pageParams.getYear(),pageParams.getCharge(),pageParams.getItype(),pageParams.getCharacteristic(),pageParams.getIpay(),pageParams.getIyear(),pageParams.getSource(),pageParams.getExclusive(),pageParams.getPlot_aspect(),pageParams.getLanguage(),pageParams.getAnime_status(),pageParams.getItrailer(),pageParams.getCuisine_style(),pageParams.getFood_itype(),pageParams.getIaspect(),pageParams.getIcolumn(),pageParams.getIcelebrity(),pageParams.getCategory(),pageParams.getTime(),pageParams.getClass_type(),pageParams.getColumn(),pageParams.getIcompany());

            if (null != htmlPart){
                Document document = Jsoup.parse(htmlPart);
                Elements listItems = document.select("div.list_item");

                listItems.forEach(element -> {
                    try {
                        String url = element.selectFirst("a.figure").attr("href");
                        String picUrl = SysContext.PROTOCOL_HTTPS + SysContext.MAO_HAO +
                                element.selectFirst("img.figure_pic").attr("src");
                        String name = element.selectFirst("div.figure_detail.figure_detail_two_row a.figure_title").text();
                        String jiNumber = null != element.selectFirst("div.figure_caption") ?
                                element.selectFirst("div.figure_caption").text() : SysContext.BLANK_STRING;
                        String introduction = null != element.selectFirst("div.figure_desc") ?
                                element.selectFirst("div.figure_desc").text() : "";
                        Map<String, Object> tmpMap = new HashMap<String, Object>();
                        tmpMap.put("title", name);
                        tmpMap.put("imgSrc", picUrl);
                        tmpMap.put("href", "/playMovieWithThreePart?playUrl=" + url);
                        tmpMap.put("jiNumber", jiNumber);
                        tmpMap.put("subTitle", introduction.substring(0,6) + "...");
                        list.add(tmpMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
