package application.controller.baiduyunwangpan;

import application.service.baiduyunwangpan.BaiduYunSearchService;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@Lazy
@Scope("singleton")
public class BaiduYunSearchController {

    @Resource(name = "baiduYunSearchService")
    private BaiduYunSearchService baiduYunSearchService;

    @RequestMapping("/getSearchPagingBaiduYunSources")
    public @ResponseBody
    List<Map<String,Object>> getSearchPagingBaiduYunSources(String keyword,
                                             String pageIndex){
        return baiduYunSearchService.getSearchPagingBaiduYunSources(keyword, pageIndex);
    }


    @RequestMapping("/baiduyunwangpanSearchResult")
    public String baiduyunwangpanSearchResult(String keyword,
                                              String pageIndex,
                                              Model model){
        model.addAttribute("keyword",keyword);
        model.addAttribute("pageIndex",pageIndex);
        return "baiduyun/baiduYunSearchResult";
    }
}
