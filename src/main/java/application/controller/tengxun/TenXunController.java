package application.controller.tengxun;

import application.model.tengxun.PageParams;
import application.model.tengxun.PageResult;
import application.service.tengxun.TenXunService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @GetMapping("/pageList")
    public @ResponseBody
    List<PageResult> pageList(
            @RequestParam("pageIndex") int pageIndex,
            @RequestParam("pageSize") int pageSize,
            PageParams pageParams
            ){
        return tenXunService.pageList(pageParams.getChannel(),(pageIndex -1) * pageSize,pageSize,pageParams.getFeature(),pageParams.getIarea(),pageParams.getPay(),pageParams.getSort(),pageParams.getYear(),pageParams.getCharge(),pageParams.getItype(),pageParams.getCharacteristic(),pageParams.getIpay(),pageParams.getIyear(),pageParams.getSource(),pageParams.getExclusive(),pageParams.getPlot_aspect(),pageParams.getLanguage(),pageParams.getAnime_status(),pageParams.getItrailer());
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

    @GetMapping("/showTenXunTv")
    public String showTenXunTv(String url, String platform, String tvName, Model model){
        model.addAttribute("url", url);
        model.addAttribute("platform", platform);
        model.addAttribute("tvName", tvName);
        return "tengxun/showTenXunTv";
    }

    /**
     * 根据指定的url进行分析出综艺节目的剧集列表
     * @param url url
     * @return List<String>
     */
    @GetMapping("/analysisVarietyShowPageToVideoList")
    public @ResponseBody List<Map<String,Object>> analysisVarietyShowPageToVideoList(@RequestParam String url){
        return tenXunService.analysisVarietyShowPageToVideoList(url);
    }

    @GetMapping("/showTenXunVariety")
    public String showTenXunVariety(String url, String platform, String varietyName, Model model){
        model.addAttribute("url", url);
        model.addAttribute("platform", platform);
        model.addAttribute("varietyName", varietyName);
        return "tengxun/showTenXunVariety";
    }
}
