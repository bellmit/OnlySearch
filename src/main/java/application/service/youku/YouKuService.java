package application.service.youku;

import application.model.youku.YouKuVideo;
import application.service.feign.youku.AcsYouKuComFeign;
import application.service.feign.youku.ListYouKuComFeign;
import application.service.feign.youku.VYouKuComFeign;
import feign.Response;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: 优酷
 * @date 2020-04-06 07:41:39
 */
@Service
public class YouKuService {

    @Resource
    private ListYouKuComFeign listYouKuComFeign;

    @Resource
    private VYouKuComFeign vYouKuComFeign;

    @Resource
    private AcsYouKuComFeign acsYouKuComFeign;

    /**
     * 获取类别的列表
     *
     * @param classify  分类
     *                  97 剧集
     *                  96 电影
     *                  85 综艺
     *                  100 动漫
     *                  177 少儿
     *                  95 音乐
     *                  87 教育
     *                  84 纪录片
     *                  98 体育
     *                  178 文化
     *                  86 娱乐
     *                  99 游戏
     *                  91 资讯
     *                  94 搞笑
     *                  103 生活
     *                  104 汽车
     *                  105 科技
     *                  89 时尚
     *                  90 亲子
     *                  88 旅游
     *                  171 微电影
     *                  172 网剧
     *                  174 拍客
     *                  175 创意视频
     * @param type      类型
     * @param time      时间
     * @param area      地区
     * @param videoType 视频类型
     * @param pageIndex 页码
     * @param stamp     类型
     * @param language  语言
     * @return json String
     */
    public String categoryList(
            int classify,
            String type,
            String time,
            String area,
            String videoType,
            int pageIndex,
            String stamp,
            String language
    ) {
        return listYouKuComFeign.categoryList(classify, type, time, area, videoType, pageIndex, stamp, language);
    }

    /**
     * 获取播放列表
     *
     * @param videoId videoId
     * @return List<YouKuVideo>
     */
    public List<YouKuVideo> videoList(String videoId) throws Exception {
        List<YouKuVideo> youKuVideos = new ArrayList<>();
        Response response = vYouKuComFeign.videoListPage(videoId);

        InputStream inputStream = response.body().asInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = -1;
        byte [] buffer = new byte[1024];
        while((length = inputStream.read(buffer)) != -1){
            byteArrayOutputStream.write(buffer,0,length);
        }

        String html = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
        System.out.println(html);
        String json = html.
                split("window\\.__INITIAL_DATA__ =")[1].
                split(";</script>")[0];
        JSONObject dataJsonObj = JSONObject.fromObject(json);

        //获取showId
        String showId = dataJsonObj.getString("showId");

        Response responseToken = acsYouKuComFeign.getTokens();
        Collection<String> cookies = responseToken.headers().get("set-cookie");

        String mH5TkEnc = null;
        String mH5Tk = null;
        String token = null;

        /*
         * 获取cookies
         */
        for (String cookie : cookies){
            if (cookie.contains("_m_h5_tk_enc")){
                mH5TkEnc = cookie.split(";")[0] + ";";
            }
            else if (cookie.contains("_m_h5_tk")){
                mH5Tk = cookie.split(";")[0] + ";";
            }
        }

        //获取token
        if (null != mH5Tk){
            token = mH5Tk.split("=")[1].split("_")[0];
        }

        if (null != token){
            stop:

            for (int i=0;i<Integer.MAX_VALUE;i++){
                String templateParamStr = "TOKEN&TIME_MILLIS&24679788&{\"ms_codes\":\"2019030100\",\"params\":\"{\\\"biz\\\":\\\"new_detail_web\\\",\\\"scene\\\":\\\"component\\\",\\\"componentVersion\\\":\\\"3\\\",\\\"ip\\\":\\\"219.142.146.103\\\",\\\"debug\\\":0,\\\"utdid\\\":\\\"IpBGF6mIHiECAduOkmfpHfiB\\\",\\\"userId\\\":\\\"\\\",\\\"platform\\\":\\\"pc\\\",\\\"nextSession\\\":\\\"{\\\\\\\"componentIndex\\\\\\\":\\\\\\\"3\\\\\\\",\\\\\\\"componentId\\\\\\\":\\\\\\\"61518\\\\\\\",\\\\\\\"level\\\\\\\":\\\\\\\"2\\\\\\\",\\\\\\\"itemPageNo\\\\\\\":\\\\\\\"0\\\\\\\",\\\\\\\"lastItemIndex\\\\\\\":\\\\\\\"0\\\\\\\",\\\\\\\"pageKey\\\\\\\":\\\\\\\"LOGICSHOW_LOGICTV_DEFAULT\\\\\\\",\\\\\\\"group\\\\\\\":\\\\\\\"0\\\\\\\",\\\\\\\"itemStartStage\\\\\\\":ITEM_START_PAGE,\\\\\\\"itemEndStage\\\\\\\":ITEM_END_PAGE}\\\",\\\"videoId\\\":\\\"VIDEO_ID\\\",\\\"showId\\\":\\\"SHOW_ID\\\"}\",\"system_info\":\"{\\\"os\\\":\\\"pc\\\",\\\"device\\\":\\\"pc\\\",\\\"ver\\\":\\\"1.0.0\\\",\\\"appPackageKey\\\":\\\"pcweb\\\",\\\"appPackageId\\\":\\\"pcweb\\\"}\"}";
                long timeMillis = System.currentTimeMillis();
                templateParamStr = templateParamStr.replaceAll("TOKEN",token).
                        replaceAll("TIME_MILLIS",String.valueOf(timeMillis)).
                        replaceAll("ITEM_START_PAGE",String.valueOf(i * 30 + 1)).
                        replaceAll("ITEM_END_PAGE",String.valueOf((i+1) * 30)).
                        replaceAll("VIDEO_ID",videoId).
                        replaceAll("SHOW_ID",showId);

                ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("javascript");

                scriptEngine.eval(new BufferedReader(new InputStreamReader(YouKuService.class.getResourceAsStream("/static/jss/youku/youku.js"))));
                Invocable invocable = (Invocable) scriptEngine;
                String sign = String.valueOf(invocable.invokeFunction("youkuFunc", templateParamStr));

                String videoJsonStr = acsYouKuComFeign.getVideoList(timeMillis, sign, templateParamStr.split("&")[3],mH5Tk + mH5TkEnc);

                JSONArray jsonArray = JSONObject.fromObject(videoJsonStr).getJSONObject("data").getJSONObject("2019030100")
                        .getJSONObject("data")
                        .getJSONArray("nodes");
                for (int j=0;j<jsonArray.size();j++){
                    JSONObject jsonObject = jsonArray.getJSONObject(j).getJSONObject("data");
                    String imgUrl = jsonObject.getString("img");
                    String title = jsonObject.getString("title");
                    String videoUrl = "https://v.youku.com/v_show/id_" + jsonObject.getJSONObject("action").getString("value") + ".html";

                    for (YouKuVideo youKuVideo : youKuVideos){
                        if (youKuVideo.getVideoUrl().equalsIgnoreCase(videoUrl)){
                            break stop;
                        }
                    }
                    youKuVideos.add(
                            new YouKuVideo(
                                    imgUrl,title,videoUrl
                            )
                    );
                }
            }
        }
        return youKuVideos;
    }
}
