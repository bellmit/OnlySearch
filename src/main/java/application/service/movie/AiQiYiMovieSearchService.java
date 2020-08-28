package application.service.movie;

import application.service.feign.movie.AiqiyiMovieFegin;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wtl
 */
@Service
public class AiQiYiMovieSearchService implements MovieSearchServiceInterface {

    @Autowired
    private AiqiyiMovieFegin aiqiyiMovieFegin;

    @Override
    public void analyseBigData(Map<String, Object> map,
                               List<Map<String, Object>> list,
                               int pageIndex) {
        String json = aiqiyiMovieFegin.analyseBigData(pageIndex);
        JSONArray jsonArray = JSONObject.fromObject(json).getJSONObject("data").getJSONArray("list");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            tmpMap.put("title", jsonObject.getString("name"));
            tmpMap.put("imgSrc", jsonObject.getString("imageUrl"));
            tmpMap.put("href", "/playMovieWithThreePart?playUrl=" + jsonObject.getString("playUrl") + "&name=" + jsonObject.getString("name"));
            tmpMap.put("jiNumber", jsonObject.getString("duration"));
            tmpMap.put("subTitle", jsonObject.containsKey("focus") ? jsonObject.get("focus") : "");
            list.add(tmpMap);
        }

    }
}
