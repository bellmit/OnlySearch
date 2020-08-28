package application.service.image;

import application.model.image.Image;
import application.service.feign.image.BaiduImageFeign;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageSearchService {

    @Autowired
    private BaiduImageFeign baiduImageFeign;

    public Object getAllMatchingImages(String keyword,
                                            int pageIndex,
                                            int pageSize){
        List<Image> images = new ArrayList<Image>();
        try {
            String json = baiduImageFeign.searchByKeyword(URLEncoder.encode(keyword, "utf-8"),pageIndex,pageSize);

            System.out.println(json);
            if (json != null && !"".equalsIgnoreCase(json)){
                JSONArray jsonArray = JSONObject.fromObject(json).getJSONArray("data");

                for (int i=0;i<jsonArray.size()-1;i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    images.add(new Image(jsonObject.getString("bdImgnewsDate")
                            ,jsonObject.getString("fromPageTitle")
                            ,jsonObject.getString("fromPageTitleEnc")
                            ,jsonObject.getString("fromURL")
                            ,jsonObject.getString("fromURLHost")
                            ,jsonObject.getString("middleURL")
                            ,jsonObject.getString("thumbURL")
                            ,jsonObject.getString("type")
                            ,jsonObject.getInt("width")));
                }
            }

        }
        catch (Exception e){
            //
        }

        return images;
    }
}
