package application.controller.tengxun;

import application.model.tengxun.PageParams;
import application.model.tengxun.PageResult;
import application.service.tengxun.TenXunService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: wtl
 * @License: (C) Copyright 2020. Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020-09-12 16:30
 * @Version: 1.0
 * @Description:
 */
@Controller
@RequestMapping("/tengxun")
public class TenXunController {

    @Resource
    private TenXunService tenXunService;

    /**
     * 获取腾讯视频电视剧的接口
     * @param pageParams PageParams
     * @return List<PageResult>
     */
    @GetMapping("/pageTvList")
    public @ResponseBody
    List<PageResult> pageTvList(
            @RequestParam("pageIndex") int pageIndex,
            @RequestParam("pageSize") int pageSize,
            PageParams pageParams
            ){
        return tenXunService.pageTvList(pageIndex,pageSize,pageParams.getFeature(),pageParams.getIarea(),pageParams.getPay(),pageParams.getSort(),pageParams.getYear());
    }

    @GetMapping("/pageIndex")
    public String pageIndex(){
        return "tengxun/index";
    }

    /**
     * 根据指定的url进行分析出电视剧的剧集列表
     * @param url 待分析的url
     * @return List<String>
     */
    @GetMapping("/analysisPageToList")
    public @ResponseBody List<String> analysisPageToList(@RequestParam("url") String url){
        return tenXunService.analysisPageToList(url);
    }
}
