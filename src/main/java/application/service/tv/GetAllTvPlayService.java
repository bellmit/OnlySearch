package application.service.tv;

import application.model.tv.TvCls;
import application.service.feign.tv.AiqiyiTvFeign;
import application.service.tengxun.TenXunService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Administrator
 */
@Service
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
public class GetAllTvPlayService {

    @Resource
    private AiqiyiTvFeign aiqiyiTvFeign;

    @Resource
    private TenXunService tenXunService;

    @Resource
    private List<TVPlaySearchServiceInterface> tvPlaySearchServiceInterfaces;

    public Map<String, Object> getAllResults(int pageIndex) {
        System.out.println(tvPlaySearchServiceInterfaces.size());
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        map.put("tvList", list);

        for (int i = 0; i < tvPlaySearchServiceInterfaces.size(); i++) {
            tvPlaySearchServiceInterfaces.get(i).analyseBigData(map, list, pageIndex);
        }

        return map;
    }

    /**
     * 展示TV
     *
     * @param url url
     * @param aid aid（爱奇艺）
     * @param platform 平台
     * @param name tv名称
     * @return Set<TvCls>
     */
    public Set<TvCls> showTv(String url, String aid, String platform,String name) {
        switch (platform){
            case "tengxun":
                return dealTenXunTv(url,name);
            case "iqiyi":
                return dealAqiyiTv(url,aid);
            default:
                break;
        }
        return null;
    }

    /**
     * 处理aqiyi的tv
     * @param url url
     * @param aId aid（爱奇艺）
     * @return Set<TvCls>
     */
    private Set<TvCls> dealAqiyiTv(String url, String aId) {
        String result = aiqiyiTvFeign.showTv(aId, 1,Integer.MAX_VALUE);
        JSONObject jsonObject = JSONObject.fromObject(result);
        System.out.println("###########################################");
        System.out.println(jsonObject);
        System.out.println("###########################################");
        JSONArray epsodelist = jsonObject.getJSONObject("data").getJSONArray("epsodelist");
        Set<TvCls> set = new TreeSet<TvCls>();
        for (int i = 0; i < epsodelist.size(); i++) {
            JSONObject epsodelistJSONObject = epsodelist.getJSONObject(i);
            set.add(new TvCls(
                    epsodelistJSONObject.getInt("order"),
                    epsodelistJSONObject.getString("name"),
                    epsodelistJSONObject.getString("playUrl")));
        }
        return set;
    }

    /**
     * 处理腾讯tv的剧集展示
     * @param url url
     * @param name tv名
     * @return Set<TvCls>
     */
    private Set<TvCls> dealTenXunTv(String url,String name){
        List<String> urlList = tenXunService.analysisPageToList(url);
        Set<TvCls> tvClsSet = new TreeSet<>();
        for (int i=0;i<urlList.size();i++){
            tvClsSet.add(TvCls.builder()
            .index(i+1)
            .name(name)
            .url(urlList.get(i))
            .build());
        }
        return tvClsSet;
    }
}
