package application.service.app;

import application.model.app.App;
import application.service.feign.app.ClassifyAppsFeign;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wtl
 */
@Service
public class AppBigDataService {

    @Autowired
    private ClassifyAppsFeign classifyAppsFeign;

    /**
     * 获得分类并分页后的apps
     * @return
     */
    public Map<String,Object> getClassifyApps(String classifyName,
                                              int categoryId,
                                              int pageSize,
                                              int pageContext){
        Map<String,Object> map = new HashMap<String, Object>();
        List<App> apps = new ArrayList<App>();
        map.put("apps",apps);
        map.put("classifyName",classifyName);

        String json = classifyAppsFeign.getClassifyApps(categoryId, pageSize, pageContext);

        if (!"".equalsIgnoreCase(json)){
            try {
                JSONArray jsonArray = (JSONArray) JSONObject.fromObject(json).get("obj");

                for (Object o : jsonArray) {
                    JSONObject jsonObject = (JSONObject) o;
                    List<String> images = new ArrayList<String>();
                    JSONArray imageJsonArray = (JSONArray) jsonObject.get("images");
                    for (int j = 0; j < imageJsonArray.size(); j++) {
                        images.add(imageJsonArray.getString(j));
                    }
                    App app = new App(jsonObject.getString("apkMd5")
                            , jsonObject.getLong("apkPublishTime")
                            , jsonObject.getString("apkUrl")
                            , jsonObject.getLong("appDownCount")
                            , jsonObject.getInt("appId")
                            , jsonObject.getString("appName")
                            , jsonObject.getInt("authorId")
                            , jsonObject.getString("authorName")
                            , jsonObject.getDouble("averageRating")
                            , jsonObject.getInt("categoryId")
                            , jsonObject.getString("categoryName")
                            , jsonObject.getString("description")
                            , jsonObject.getString("editorIntro")
                            , jsonObject.getLong("fileSize")
                            , jsonObject.getString("iconUrl")
                            , images
                            , jsonObject.getString("newFeature")
                            , jsonObject.getString("pkgName")
                            , jsonObject.getString("snapshotsUrl")
                            , jsonObject.getString("versionName"));

                    apps.add(app);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        return map;
    }
}
