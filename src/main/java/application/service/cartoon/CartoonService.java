package application.service.cartoon;

import application.model.cartoon.CartoonChapter;
import application.model.cartoon.CartoonIndexLink;
import application.model.cartoon.CartoonPageContent;
import application.model.cartoon.CartoonSearchContent;
import application.service.feign.cartoon.CartoonFeign;
import feign.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartoonService {

    private final static String BASE_URL = "https://m.gufengmh8.com";

    @Autowired
    private CartoonFeign cartoonFeign;

    public List<List<CartoonIndexLink>>  cartoonIndex() throws Exception {
        List<List<CartoonIndexLink>> cartoonIndexLinks = new ArrayList<List<CartoonIndexLink>>();

        Response response = cartoonFeign.index();

        Document document = Jsoup.parse(common_code2(response));
        //type
        common_codes(document,
                cartoonIndexLinks,
                "#w0 > div > div:nth-child(1) > ul",
                "li a");

        //地区
        common_codes(document,
                cartoonIndexLinks,
                "#w0 > div > div:nth-child(2) > ul",
                "li a");

        //剧情
        common_codes(document,
                cartoonIndexLinks,
                "#w0 > div > div:nth-child(3) > ul",
                "li a");

        //按字母
        common_codes(document,
                cartoonIndexLinks,
                "#w0 > div > div:nth-child(4) > ul",
                "li a");

        //按进度
        common_codes(document,
                cartoonIndexLinks,
                "#w0 > div > div:nth-child(5) > ul",
                "li a");

        return cartoonIndexLinks;
    }

    private void common_codes(Document document,
                              List<List<CartoonIndexLink>> cartoonIndexLinks,
                              String cssSelector_ul,
                              String cssSelector_li_a){
        Element element_type = document.selectFirst(cssSelector_ul);
        Elements elements_lis = element_type.select(cssSelector_li_a);
        List<CartoonIndexLink> cartoonIndexLinks_type = new ArrayList<CartoonIndexLink>();
        cartoonIndexLinks.add(cartoonIndexLinks_type);
        for (Element element : elements_lis){
            cartoonIndexLinks_type.add(new CartoonIndexLink(element.attr("href"),element.text()));
        }
    }


    /**
     * 根据prefixUrll、pageIndex获取分类的列表
     * @param prefixUrl
     * @param pageIndex
     * @return
     * @throws Exception
     */
    public List<CartoonPageContent> getPageContentJson(String prefixUrl, int pageIndex, String classfyName) throws Exception {
        String listHtml = cartoonFeign.getListHtml(prefixUrl, 1000000000);
        Document document = Jsoup.parse(listHtml);

        int pageSize = -1;
        try {
            pageSize = Integer.parseInt(document.selectFirst("li.active a").text());
        }
        catch (Exception e){
            pageSize = 1;
        }

        List<CartoonPageContent> cartoonPageContents = new ArrayList<CartoonPageContent>();
        if (pageIndex <= pageSize){
            Response response = cartoonFeign.list1(prefixUrl, pageIndex);

            Document document_outer = Jsoup.parse(common_code2(response));
            Elements oLis = document_outer.select("li.list-comic");
            List<Integer> indexs = new ArrayList<Integer>();
            for (int i=0;i<pageSize;i++){
                indexs.add(i+1);
            }
            for (Element element : oLis){
                cartoonPageContents.add(new CartoonPageContent(
                        element.selectFirst("a.ImgA").attr("href"),
                        element.selectFirst("mip-img").attr("src"),
                        element.selectFirst("a.txtA").text(),
                        element.selectFirst("span.info > a").text(),
                        pageSize,
                        indexs));
            }
        }

        return cartoonPageContents;
    }

    private String common_code2(Response response) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(response.body().asReader());
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while((line = bufferedReader.readLine())!=null){
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    /**
     * 获取页面内容，并返回相关信息
     * @param url
     * @return
     */
    public Map<String, Object> cartoonContent(String url) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        Response response = cartoonFeign.list2(url.replaceAll("https://m.gufengmh8.com", ""));
        String html = common_code2(response);
        Document document = Jsoup.parse(html);
        //测试是否存在该漫画
        Element testNode = document.selectFirst("#Cover > mip-img");

        if (null != testNode){
            Element element_title = document.selectFirst("body > div.comic-view.clearfix > div.view-sub.autoHeight > h1");
            Element element_update = document.selectFirst("body > div.comic-view.clearfix > div.view-sub.autoHeight > div > dl:nth-child(2)");
            Element element_author = document.selectFirst("body > div.comic-view.clearfix > div.view-sub.autoHeight > div > dl:nth-child(3)");
            Element element_type = document.selectFirst("body > div.comic-view.clearfix > div.view-sub.autoHeight > div > dl:nth-child(4)");
            Element element_updateDate = document.selectFirst("body > div.comic-view.clearfix > div.view-sub.autoHeight > div > dl:nth-child(5)");

            List<String> list = new ArrayList<String>();
            map.put("textList",list);
            if (null != element_title){
                list.add(element_title.text());
            }

            if (null != element_update){
                list.add(element_update.text());
            }

            if (null != element_author){
                list.add(element_author.text());
            }

            if (null != element_type){
                list.add(element_type.text());
            }

            if (null != element_updateDate){
                list.add(element_updateDate.text());
            }

            List<CartoonChapter> chapterList = new ArrayList<CartoonChapter>();
            map.put("chapterList",chapterList);
            Elements elements = document.select("div.list > ul > li > a");
            for (Element element : elements){
                chapterList.add(new CartoonChapter(BASE_URL + element.attr("href"),
                        element.text()));
            }
        }

        return map;
    }

    /**
     * 获取章节中的第index个图片
     * @param chapterUrl
     * @param index
     * @return
     */
    public String getChapterToAImage(String chapterUrl,int index) throws Exception {
        chapterUrl= chapterUrl.replaceAll("https://m.gufengmh8.com", "")
                .replaceAll("\\.html","-" + index + ".html");
        Response response = cartoonFeign.list2(chapterUrl);

        if (200 == response.status()){
            String html = common_code2(response);

            Document document = Jsoup.parse(html);

            Element image_element = document.selectFirst("#chapter-view > div.chapter-content > div:nth-child(1) > a > img");

            if (null != image_element){
                return image_element.attr("src");
            }
        }

        return "404";
    }


    /**
     * 搜索功能
     * @param keyword 关键词
     * @return
     */
    public List<CartoonSearchContent> search(String keyword){
        List<CartoonSearchContent> list = new ArrayList<>();
        String html = cartoonFeign.search(keyword, 1);
        Document document = Jsoup.parse(html);
        Elements elements = document.selectFirst("ul.pagination").select("a");
        Element element = elements.get(elements.size() - 1);
        int pageSize = Integer.parseInt(element.attr("data-page")) + 1;
        for (int i=0;i<pageSize;i++){
            String innerHtml = cartoonFeign.search(keyword, i + 1);
            Document doc = Jsoup.parse(innerHtml);
            Elements els = doc.select("div#update_list div.UpdateList div.itemBox div.itemTxt");
            for(Element ele : els){
                Elements pEles = ele.select("p.txtItme");
                CartoonSearchContent cartoonSearchContent = CartoonSearchContent
                        .builder()
                        .url(ele.selectFirst("a").attr("href"))
                        .title(ele.selectFirst("a").text())
                        .imgSrc(ele.parent().selectFirst("div.itemImg mip-img").attr("src"))
                        .author(pEles.get(0).text())
                        .updateTime(pEles.get(pEles.size() - 1).text())
                        .build();

                list.add(cartoonSearchContent);
            }
        }
        return list;
    }
}
