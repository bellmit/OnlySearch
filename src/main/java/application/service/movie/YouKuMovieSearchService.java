package application.service.movie;

import application.service.feign.movie.YoukuMovieFeign;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wtl
 */
//@Service
public class YouKuMovieSearchService implements MovieSearchServiceInterface {

    @Resource
    private YoukuMovieFeign youkuMovieFeign;
    @Override
    public void analyseBigData(Map<String, Object> map,
                               List<Map<String, Object>> list,
                               int pageIndex) {
        String json = youkuMovieFeign.searchMovie(pageIndex);

        JSONArray jsonArray = JSONObject.fromObject(json).getJSONArray("data");

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            tmpMap.put("title", jsonObject.getString("title"));
            tmpMap.put("imgSrc", "https:" + jsonObject.getString("img"));
            tmpMap.put("href", "/playMovieWithThreePart?playUrl=" + "https:" + jsonObject.getString("videoLink") + "&name=" + jsonObject.getString("title"));
            tmpMap.put("jiNumber", jsonObject.getString("summary"));
            tmpMap.put("subTitle", jsonObject.getString("subTitle"));

            list.add(tmpMap);
        }
    }
}
