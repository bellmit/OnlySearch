package application.service.dianyingtiantang;

import application.model.dianyingtiantang.DownloadSource;
import application.model.dianyingtiantang.Information;
import application.service.feign.dianyingtiantang.DianYingTianTangFeign;
import application.service.feign.dianyingtiantang.DianYingTianTangSearchFeign;
import application.service.feign.dianyingtiantang.DianYingTianTangSearchResultFeign;
import feign.Feign;
import feign.Response;
import feign.Target;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DianYingTianTangSearchService {


    @Autowired
    private DianYingTianTangFeign dianYingTianTangFeign;

    private DianYingTianTangSearchFeign dianYingTianTangSearchFeign;

    @Autowired
    private DianYingTianTangSearchResultFeign dianYingTianTangSearchResultFeign;

    @Autowired
    public DianYingTianTangSearchService(){
        dianYingTianTangSearchFeign = Feign.builder().
                encoder(new GsonEncoder()).
                decoder(new GsonDecoder()).
                target(Target.EmptyTarget.create(DianYingTianTangSearchFeign.class));
    }

    private List<Information> commonCode(String content, int type , int pageIndex) throws Exception {
        List<Information> informations = new ArrayList<Information>();
        InputStream inputStream = dianYingTianTangFeign.getPageingContent(content,type, pageIndex).body().asInputStream();
        byte [] buffer = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = -1;
        while((length = inputStream.read(buffer))!=-1){
            byteArrayOutputStream.write(buffer,0,length);
        }

        String html = new String(byteArrayOutputStream.toByteArray(),"gbk");

        System.out.println(html);

        if (html.length() > 0) {
            Document document = Jsoup.parse(html);
            Elements newMoviesTables = document.select("div.co_content8 ul table");
            for (Element table : newMoviesTables) {
                informations.add(new Information(table.select("a").size() == 1 ? table.select("a").get(0).text() : table.select("a").get(1).text()
                        , "https://www.ygdy8.com" + (table.select("a").size() == 1 ? table.select("a").get(0).attr("href") : table.select("a").get(1).attr("href"))
                        , table.selectFirst("font").text().split("点击")[0]
                        , table.select("td").get(table.select("td").size() - 1).text()
                        , Integer.parseInt(document.select("select option").get(document.select("select option").size() - 1).text())));
            }
        }
        return informations;
    }

    /**
     * 获得最新影片
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingNewMovies(String pageIndex) throws Exception {
        return commonCode("gndy/dyzz",23,Integer.parseInt(pageIndex));
    }

    /**
     * 获得国内电影
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingChinaMovies(String pageIndex) throws Exception {
        return commonCode("gndy/china",4,Integer.parseInt(pageIndex));
    }


    /**
     * 获得欧美电影
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingEuropeansMovies(String pageIndex) throws Exception {
        return commonCode("gndy/oumei",7,Integer.parseInt(pageIndex));
    }


    /**
     * 获得华语电视
     * @param pageIndex
     * @return
     */
    public List<Information> getPaginghuaYuTvs(String pageIndex) throws Exception {
        return commonCode("tv/hytv",71,Integer.parseInt(pageIndex));
    }


    /**
     * 获得日韩电视
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingRiHanTvs(String pageIndex) throws Exception {
        return commonCode("tv/rihantv",8,Integer.parseInt(pageIndex));
    }

    /**
     * 获得欧美电视
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingOuMeiTvs(String pageIndex) throws Exception {
        return commonCode("tv/oumeitv",9,Integer.parseInt(pageIndex));
    }


    /**
     * 获得最新综艺
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingNewZongYis(String pageIndex) throws Exception {
        return commonCode("zongyi2013",99,Integer.parseInt(pageIndex));
    }



    /**
     * 获得旧版综艺
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingOldZongYis(String pageIndex) throws Exception {
        return commonCode("2009zongyi",89,Integer.parseInt(pageIndex));
    }


    /**
     * 获得动漫
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingDongMans(String pageIndex) throws Exception {
        return commonCode("dongman",16,Integer.parseInt(pageIndex));
    }

    /**
     * 获得游戏
     * @param pageIndex
     * @return
     */
    public List<Information> getPagingGames(String pageIndex) throws Exception {
        return commonCode("game",19,Integer.parseInt(pageIndex));
    }


    /**
     * 获得详情下载页
     * @param downloadPageUrl
     * @return
     */
    public List<DownloadSource> getDownloads(String downloadPageUrl) throws Exception {
        List<DownloadSource> downloadSources = new ArrayList<DownloadSource>();

        Response response = dianYingTianTangSearchFeign.getDownloadPageContent(new URI(downloadPageUrl));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(response.body().asInputStream());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        IOUtils.copy(bufferedInputStream,byteArrayOutputStream);

        String html = new String(byteArrayOutputStream.toByteArray(),"gb2312");

        if (!"".equalsIgnoreCase(html)) {
            Document document = Jsoup.parse(html);
            Element container = document.selectFirst("div.co_content8 ul");
            DownloadSource downloadSource = new DownloadSource();

            downloadSource.setDescription(container.selectFirst("div#Zoom span > p").html());
            Elements as = document.selectFirst("div#Zoom").select("table a");

            if (as != null && as.size() >= 1){
                List<String> fileNames = new ArrayList<String>();
                List<String> downloadUrls = new ArrayList<String>();

                for (int i=0;i<as.size();i++){
                    fileNames.add(as.get(i).text());
                    Attributes attributes = as.get(i).attributes();

                    Iterator<Attribute> attributeIterator = attributes.iterator();
                    while (attributeIterator.hasNext()){
                        String _url = attributeIterator.next().getValue();
                        downloadUrls.add(_url);
                    }
                }

                downloadSource.setFileNames(fileNames);
                downloadSource.setDownloadUrls(downloadUrls);

                downloadSources.add(downloadSource);
            }
        }
        return downloadSources;
    }

    /**
     * 搜索分页结果
     * @param keyword
     * @param typeid
     * @param pageIndex
     * @return
     */
    public List<Information> getSearchPagingResult(String keyword,
                                                   int typeid,
                                                   int pageIndex){
        List<Information> informations = new ArrayList<Information>();

        try {
            Response response = dianYingTianTangSearchResultFeign.getSearchPagingResult(URLEncoder.encode(keyword,"gbk"),typeid,pageIndex);
            InputStream inputStream = response.body().asInputStream();
            int length = -1 ;
            byte [] buffer = new byte[10240];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while ((length = inputStream.read(buffer))!=-1){
                byteArrayOutputStream.write(buffer,0,length);
            }
            String html = new String(byteArrayOutputStream.toByteArray(),"gbk");

            inputStream.close();
            byteArrayOutputStream.close();

            if (!"".equalsIgnoreCase(html)){
                Document document = Jsoup.parse(html);

                Elements tables = document.select("table[width='100%']");

                if (tables != null && tables.size() > 0){
                    for (int i=0;i<tables.size();i++){
                        try {
                            informations.add(new Information(tables.get(i).selectFirst("a").text()
                                    ,"https://www.ygdy8.com"+tables.get(i).selectFirst("a").attr("href")
                                    ,null
                                    ,tables.get(i).select("td").get(1).text()
                                    ,document.selectFirst("input[name=\"TotalResult\"]") != null ? (int)Math.ceil(Integer.parseInt(document.selectFirst("input[name=\"TotalResult\"]").attr("value")) *1.0 /10.0 ) : 1));
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return informations;
    }
}
