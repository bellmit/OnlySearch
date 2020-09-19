package application.service.tv;

import application.model.tv.TvCls;
import application.service.feign.tv.AiqiyiTvFeign;
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
     * @param url
     * @param platform
     * @return
     */
    public Set<TvCls> showTv(String url, String aid, String platform) {
        return dealAqiyiTv(url,aid);
    }

    /**
     * 处理aqiyi的tv
     * @param url
     * @param aId
     * @return
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
}
