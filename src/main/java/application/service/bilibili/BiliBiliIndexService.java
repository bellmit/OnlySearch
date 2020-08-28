package application.service.bilibili;

import application.filter.SysContext;
import application.service.feign.bilibili.*;
import application.utils.HttpOrHttpsUrlValidatorRequestUtils;
import feign.Feign;
import feign.Target;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @author Administrator
 */
@Service
@Import(FeignClientsConfiguration.class)
public class BiliBiliIndexService {

    @Resource
    private BiliBiliIndexFeign biliBiliIndexFeign;

    @Resource
    private BiliBiliVideoFeign biliBiliVideoFeign;

    private BiliBiliDynamicFeign biliBiliDynamicFeign;

    @Resource
    private BiliBiliCnFeign biliBiliCnFeign;

    @Resource
    private ApiImjadCnFeign apiImjadCnFeign;

    @Resource
    private SearchBiiliBiliComFeign searchBiiliBiliComFeign;

    private static final String RANK_URL = "https://www.bilibili.com/ranking";

    private static int index = 0;


    @Autowired
    public BiliBiliIndexService() {
        biliBiliDynamicFeign = Feign.builder().
                encoder(new GsonEncoder()).
                decoder(new GsonDecoder()).
                requestInterceptor(template -> {
                    template.header("Referer", "https://www.bilibili.com/video/");
                    template.header("Accept-Encoding", "gzip, deflate, br");
                    template.header("Cache-Control", "no-cache");
                    template.header("User-Agent", "PostmanRuntime/7.22.0");
                    template.header("Postman-Token", "5b6adac9-edaa-4711-99cc-2e64cfadd5ba");
                }).
                target(Target.EmptyTarget.create(BiliBiliDynamicFeign.class));
    }


    /**
     * 获取url页面的所有热门标签
     *
     * @param url url页面
     * @return 所有热门标签
     */
    public List<List<String>> getHotTag(String url) throws Exception {
        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < SysContext.TRY_COUNT; i++) {
            String html = HttpOrHttpsUrlValidatorRequestUtils.requestHttpsGet(url, null, null, SysContext.ENCODING, null);
            Document document = Jsoup.parse(Objects.requireNonNull(html));
            Elements oLis = document.select("div.tag-list-wrp ul.tag-list li.tag-item");
            for (org.jsoup.nodes.Element oLi : oLis) {
                List<String> tmpList = new ArrayList<>();
                tmpList.add(oLi.attr("title"));
                tmpList.add(oLi.selectFirst("a.tag-a").attr("href").split("/")[1]);
                list.add(tmpList);
            }
            if (list.size() > 0) {
                break;
            }
        }
        return list;
    }

    public Map<String, Object> getRidVideo(String rid, int pageIndex) {
        Map<String, Object> map = new HashMap<>(2);
        String newList = biliBiliVideoFeign.getNewList(rid, pageIndex);
        map.put("newList", JSONObject.fromObject(newList));
        String dynamicList = biliBiliVideoFeign.getDynamic(rid, pageIndex);
        map.put("dynamicList", JSONObject.fromObject(dynamicList).getJSONObject("data").getJSONArray("archives"));
        String rankingList = biliBiliVideoFeign.getRanking(rid);
        map.put("rankingList", JSONObject.fromObject(rankingList).getJSONArray("data"));
        return map;
    }

    public Object getDynamicList(String rid, int pageIndex) {
        String dynamicList = biliBiliVideoFeign.getDynamic(rid, pageIndex);
        return JSONObject.fromObject(dynamicList).getJSONObject("data").getJSONArray("archives");
    }

    public Object getNewList(String rid, int pageIndex) {
        String newList = biliBiliVideoFeign.getNewList(rid, pageIndex);
        return JSONObject.fromObject(newList);
    }

    public Object getNewListByTagId(String rid, int tagId, int pageIndex) {
        String newList = biliBiliVideoFeign.getNewListByTagId(rid, pageIndex, tagId);
        return JSONObject.fromObject(newList);
    }

    public Object getDynamicListByTagId(String rid, int tagId, int pageIndex) {
        String dynamicList = biliBiliVideoFeign.getDynamicByTagId(rid, tagId, pageIndex);
        return JSONObject.fromObject(dynamicList).getJSONObject("data").getJSONArray("archives");
    }

    public Map<String, Object> getRidVideoByTagId(String rid, int tagId, int pageIndex) {
        Map<String, Object> map = new HashMap<>(2);
        String newList = biliBiliVideoFeign.getNewListByTagId(rid, pageIndex, tagId);
        map.put("newList", JSONObject.fromObject(newList));
        String dynamicList = biliBiliVideoFeign.getDynamicByTagId(rid, pageIndex, tagId);
        map.put("dynamicList", JSONObject.fromObject(dynamicList).getJSONObject("data").getJSONArray("archives"));
        String rankingList = biliBiliVideoFeign.getRankingByTagId(rid, tagId);
        map.put("rankingList", JSONObject.fromObject(rankingList).getJSONArray("data"));
        return map;
    }

    /**
     * 获取哔哩哔哩的videoPage
     *
     * @param aid aid
     * @return Object
     */
    public Object getBiliBiliVideoPageCids(String aid) {
        return biliBiliVideoFeign.getBiliBiliVideoPageCids(aid);
    }

    /**
     * 获取bilibili的bideo的info
     *
     * @param aid aid
     * @param cid cid
     * @return String url
     * @throws Exception 抛出的异常
     */
    public String getBiliBiliVideoUrl(String aid, String cid) throws Exception {
        String json = biliBiliVideoFeign.getBiliBiliVideoInfo(aid, cid);
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("durl");
        System.out.println(jsonArray.getJSONObject(0).getString("url"));
        return jsonArray.getJSONObject(0).getString("url");
    }

    /**
     * @param cid
     * @param aVid
     * @param httpServletResponse
     * @return
     * @throws Exception
     */
    public void getVideoStream(String cid,String aVid,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) throws Exception {
        String json = biliBiliVideoFeign.getBiliBiliVideo(cid,aVid);
        String url = JSONObject.fromObject(json).getJSONObject("data").getJSONArray("durl").getJSONObject(0).getString("url");
        //GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        //设置头信息
        Map<String,String> headers = new HashMap<>();
        headers.put("Referer","https://api.bilibili.com/x/player/playurl");
        Object [] objects = HttpOrHttpsUrlValidatorRequestUtils.getRequester(url, null, headers, "GET", "utf-8", MediaType.APPLICATION_OCTET_STREAM_VALUE, null);
        System.out.println(Arrays.asList(objects));

        InputStream inputStream = (InputStream) objects[0];

        long fSize = (long) objects[1];
        httpServletResponse.setHeader("content-length",String.valueOf(fSize));
        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setHeader("Accept-Ranges", "bytes");
        long pos = 0;
        if (null != httpServletRequest.getHeader("Range")) {
            // 断点续传
            httpServletResponse.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            try {
                pos = Long.parseLong(httpServletRequest.getHeader("Range").replaceAll("bytes=", "").replaceAll("-", ""));
            } catch (NumberFormatException e) {
                pos = 0L;
            }
        }

        httpServletResponse.setHeader("Content-Range", "bytes " + pos + "-" + (fSize - 1) + "/" + fSize);
        long skip = inputStream.skip(pos);
        int length = -1;
        byte [] buffer = new byte[10240];
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
        while ((length = inputStream.read(buffer))!=-1){
            bufferedOutputStream.write(buffer,0,length);
        }

        inputStream.close();
        bufferedOutputStream.close();
    }

    /**
     * 获取视频信息
     *
     * @param appKey    appkey
     * @param aid       aid
     * @param pageIndex 页码
     * @return json String
     */
    public Object getVideoInfo(String appKey, String aid, int pageIndex) {
        return biliBiliCnFeign.getVideoInfo(appKey, aid, pageIndex);
    }

    /**
     * 获取全站榜、原创榜、新番榜、影视榜、新人榜的榜单
     *
     * @param type     排行榜类型
     * @param content  排行榜内容
     * @param duration 日期限制
     * @param New      bool 是否为近期投稿
     * @return json String
     */
    public String rank(String type, String content, int duration, boolean New) {
        return apiImjadCnFeign.rank(type, content, duration, New);
    }


    /**
     * 搜索结果
     *
     * @param keyword
     * @param order
     * @param duration
     * @param tids_1
     * @return
     */
    public Map<String, Object> searchResult(String keyword,
                                            String order,
                                            String duration,
                                            String tids_1,
                                            int pageIndex) {
        Map<String, Object> map = new HashMap<>(3);
        String html = searchBiiliBiliComFeign.search(keyword, order, duration, tids_1, pageIndex);
        String json = html.split("window.__INITIAL_STATE__=")[1].split(";\\(function")[0];
        JSONObject jsonObject = JSONObject.fromObject(json).getJSONObject("flow").
                getJSONObject("getMixinFlowList-jump-duration-" + duration + "-keyword-" + keyword + "-order-" + order + "-tids_1-" + tids_1);

        System.out.println(jsonObject);

        JSONArray jsonArray = jsonObject.getJSONArray("result").size() > 10 ? jsonObject.getJSONArray("result") : jsonObject.getJSONArray("result").getJSONObject(8).getJSONArray("data");
        map.put("videoList", jsonArray.toString());
        map.put("pageNumbers", jsonObject.getJSONObject("extra").getInt("numResults"));

        //分析出时间
        Document document = Jsoup.parse(html);
        Elements elements = document.select("ul.video-list span.so-imgTag_rb");
        List<String> times = new ArrayList<>(20);
        for (int i = 0; i < elements.size(); i++) {
            times.add(elements.get(i).text());
        }
        map.put("times", times);
        return map;
    }


    /**
     * 获取该aid的video的所有的page
     * @param bVid
     * @return String
     */
    public Object getBiliBiliVideoPageBVids(String bVid){
        String biliBiliVideoPageBVids = biliBiliVideoFeign.getBiliBiliVideoPageBVids(bVid);
        JSONObject jsonObject = JSONObject.fromObject(biliBiliVideoPageBVids);
        return jsonObject.getJSONArray("data");
    }
}
