package application.service.movie;

import application.model.tengxun.PageResult;
import application.service.metv.MetvService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wtl
 * @License: (C) Copyright 2021, wtl Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2021/3/20/0020 15:40
 * @Version: 1.0
 * @Description:
 */
@Service
public class MetvMovieSearchService implements MovieSearchServiceInterface {

    @Resource
    private MetvService metvService;

    @Override
    public void analyseBigData(Map<String, Object> map, List<Map<String, Object>> list, int pageIndex) {
        List<PageResult> pageResults = metvService.getMetvPageResult(pageIndex,3);
        for (PageResult pageResult : pageResults){
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            tmpMap.put("title", pageResult.getName());
            tmpMap.put("imgSrc", pageResult.getPicUrl());
            tmpMap.put("href", "/playMovieWithThreePart?playUrl=" + pageResult.getUrl());
            tmpMap.put("jiNumber", pageResult.getJiNumber());
            tmpMap.put("subTitle", pageResult.getIntroduction().substring(0,6) + "...");
            list.add(tmpMap);
        }
    }
}
