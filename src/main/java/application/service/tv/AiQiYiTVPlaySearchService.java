package application.service.tv;

import application.service.feign.tv.AiqiyiTvFeign;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wtl
 */
@Service
public class AiQiYiTVPlaySearchService implements TVPlaySearchServiceInterface {

    @Resource
    private AiqiyiTvFeign aiqiyiTvFeign;

    @Override
    public void analyseBigData(Map<String, Object> map,
                               List<Map<String,Object>> list,
                               int pageIndex) {
        String json = aiqiyiTvFeign.searchTv(pageIndex,50);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(json);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        JSONArray jsonValues = JSONObject.fromObject(json).getJSONObject("data").getJSONArray("list");

        for (int i=0;i<jsonValues.size();i++){
            try {
                JSONObject jsonObject = jsonValues.getJSONObject(i);
                Map<String,Object> tmpMap = new HashMap<String, Object>();
                tmpMap.put("title",jsonObject.getString("name"));
                tmpMap.put("imgSrc",jsonObject.getString("imageUrl"));
                tmpMap.put("href",jsonObject.getString("playUrl"));
                tmpMap.put("jiNumber",jsonObject.getString("videoCount") + "集全");
                tmpMap.put("subTitle",jsonObject.getString("focus"));
                tmpMap.put("albumId",jsonObject.getString("albumId"));
                tmpMap.put("platform","iqiyi");
                list.add(tmpMap);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
