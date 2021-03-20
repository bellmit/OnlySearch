package application.service.metv;

import application.model.tengxun.PageResult;
import application.mybatis.mappers.MetvMapper;
import application.mybatis.model.Metv;
import application.mybatis.model.TenXun;
import application.service.feign.metv.MetvFegin;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: wtl
 * @License: (C) Copyright 2021, wtl Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2021/3/20/0020 15:11
 * @Version: 1.0
 * @Description:
 */
@Service
public class MetvService {

    @Resource
    private MetvFegin metvFegin;

    @Resource
    private MetvMapper metvMapper;


    public List<PageResult> getMetvPageResult(int pageIndex, int channelId) {
        List<PageResult> pageResults = new ArrayList<>();
        String metvMoviesStr = channelId == 2 ? metvFegin.getMetvTvList(pageIndex) : metvFegin.getMetvMovieList(pageIndex);

        JSONArray hitDocs = JSON.parseObject(metvMoviesStr).getJSONObject("data").getJSONArray("hitDocs");
        for (int i = 0; i < hitDocs.size(); i++) {
            JSONObject jsonObject = hitDocs.getJSONObject(i);
            pageResults.add(PageResult.builder()
                    .url("https://www.mgtv.com/b/" + jsonObject.getString("clipId") + "/" + jsonObject.getString("playPartId") + ".html?lastp=list_index")
                    .picUrl(jsonObject.getString("img"))
                    .name(jsonObject.getString("title"))
                    .jiNumber(jsonObject.getString("updateInfo"))
                    .introduction(jsonObject.getString("story")).build());
        }
        return pageResults;
    }

    public List<String> analysisPageToList(String vid, String cid) {
        List<String> tvUrls = new ArrayList<>();
        String pageToList = metvFegin.getPageToList(vid, cid);
        JSONObject jsonObject = JSON.parseObject(pageToList);
        jsonObject.values().forEach(jsonData -> {
            JSONObject innerJsonObject = (JSONObject) jsonData;
            System.out.println(innerJsonObject);
            JSONArray jsonArray = innerJsonObject.getJSONObject("data").getJSONArray("list");

            for (int i = 0; i < jsonArray.size(); i++) {
                String url = jsonArray.getJSONObject(i).getString("url");
                url = "https://www.mgtv.com" + url;
                tvUrls.add(url);
            }
        });
        return tvUrls;
    }

    public List<Metv> searchResult(String keyword, int channelId) {
        QueryWrapper<Metv> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("channelId", channelId);
        queryWrapper.like("title", keyword)
                .or().like("starts", keyword)
                .or().like("story", keyword);
        return metvMapper.selectList(queryWrapper);
    }

    public List<Metv> searchTvResult(String keyword) {
        return searchResult(keyword, 2);
    }

    public List<Metv> searchMovieResult(String keyword) {
        return searchResult(keyword, 3);
    }
}
