package application.service.tv;

import application.filter.SysContext;
import application.model.tengxun.PageResult;
import application.service.feign.metv.MetvFegin;
import application.service.feign.tv.TenXunTvFeign;
import application.service.metv.MetvService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wtl
 * @License: (C) Copyright 2020, wtl Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020-09-16 6:23
 * @Version: 1.0
 * @Description:
 */
@Service
public class MetvTVPlaySearchService implements TVPlaySearchServiceInterface{

    @Resource
    private MetvService metvService;

    @Override
    public void analyseBigData(Map<String, Object> map, List<Map<String, Object>> list, int pageIndex) {
        List<PageResult> pageResults = metvService.getMetvPageResult(pageIndex,2);
        for (PageResult pageResult : pageResults){
            Map<String, Object> tmpMap = new HashMap<String, Object>(5);
            tmpMap.put("title", pageResult.getName());
            tmpMap.put("imgSrc", pageResult.getPicUrl());
            tmpMap.put("href", pageResult.getUrl());
            tmpMap.put("jiNumber", pageResult.getJiNumber());
            tmpMap.put("subTitle", pageResult.getIntroduction().substring(0,6) + "...");
            tmpMap.put("platform","metv");
            list.add(tmpMap);
        }
    }
}
