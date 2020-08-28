package application.service.app;

import application.filter.SysContext;
import application.model.app.App;
import application.utils.HttpOrHttpsUrlValidatorRequestUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Lazy
@Scope("singleton")
public class AppSearchService {


    public void searchApps(String keyword,String pns,String sid,List<App> apps){
        try {
            String url = "https://sj.qq.com/myapp/searchAjax.htm";
            Map<String,String> params = new HashMap<String, String>();
            params.put("kw", URLEncoder.encode(keyword,"utf-8"));
            params.put("pns", pns);
            params.put("sid",sid);

            String json = HttpOrHttpsUrlValidatorRequestUtils.requestHttpPost(url,params,null, SysContext.ENCODING, SysContext.CONTENT_TYPE);
            if (json != null && !"".equalsIgnoreCase(json)){
//                System.out.println(json);
                JSONArray jsonArray = (JSONArray) ((JSONObject) JSONObject.fromObject(json).get("obj")).get("appDetails");

                for (int i=0;i<jsonArray.size();i++){
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    JSONArray imagesJsonArray = jsonObject.getJSONArray("images");
                    List<String> images = new ArrayList<String>();
                    for (int j=0;j<imagesJsonArray.size();j++){
                        images.add(imagesJsonArray.getString(j));
                    }
                    apps.add(new App(jsonObject.getString("apkMd5")
                    ,jsonObject.getLong("apkPublishTime")
                    ,jsonObject.getString("apkUrl")
                    ,jsonObject.getLong("appDownCount")
                    ,jsonObject.getInt("appId")
                    ,jsonObject.getString("appName")
                    ,jsonObject.getInt("authorId")
                    ,jsonObject.getString("authorName")
                    ,jsonObject.getDouble("averageRating")
                    ,jsonObject.getInt("categoryId")
                    ,jsonObject.getString("categoryName")
                    ,jsonObject.getString("description")
                    ,jsonObject.getString("editorIntro")
                    ,jsonObject.getLong("fileSize")
                    ,jsonObject.getString("iconUrl")
                    ,images
                    ,jsonObject.getString("newFeature")
                    ,jsonObject.getString("pkgName")
                    ,null
                    ,jsonObject.getString("versionName")));
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }

    }


    public List<App> searchAllAppsByKeyWord(String keyword){
        String [] strings = {"","MTA","MjA","MzA","NDA","NTA","NjA","NzA"
                ,"ODA","OTA","MTAw","MTEw","MTIw","MTMw","MTQw"};
        List<App> apps = new ArrayList<App>();
        for (int i=0;i<strings.length;i++){
            if (i == 0){
                searchApps(keyword,strings[i],"",apps);
            }
            else{
                searchApps(keyword,strings[i],"0",apps);
            }
        }
        return apps;
    }
}
