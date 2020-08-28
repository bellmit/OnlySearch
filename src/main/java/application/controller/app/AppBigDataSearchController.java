package application.controller.app;

import application.model.app.App;
import application.service.app.AppBigDataService;
import application.service.app.AppSearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class AppBigDataSearchController {

    @Resource(name = "appBigDataService")
    private AppBigDataService appBigDataService;

    @Resource(name = "appSearchService")
    private AppSearchService appSearchService;

    @RequestMapping("/getClassifyApps")
    public @ResponseBody
    Map<String,Object> getClassifyApps(int categoryId,
                                       int pageSize,
                                       int pageContext){
        String classifyName = getClassifyName(categoryId);

        return appBigDataService.getClassifyApps(classifyName,
                categoryId,
                pageSize,
                pageContext);
    }

    private String getClassifyName(int categoryId){
        String classifyName = null;

        switch (categoryId){
            case -10:
                classifyName = "腾讯软件";
                break;
            case 122:
                classifyName = "购物";
                break;
            case 102:
                classifyName = "阅读";
                break;
            case 110:
                classifyName = "新闻";
                break;
            case 103:
                classifyName = "视频";
                break;
            case 108:
                classifyName = "旅游";
                break;
            case 115:
                classifyName = "工具";
                break;
            case 106:
                classifyName = "社交";
                break;
            case 101:
                classifyName = "音乐";
                break;
            case 119:
                classifyName = "美化";
                break;
            case 104:
                classifyName = "摄影";
                break;
            case 114:
                classifyName = "理财";
                break;
            case 117:
                classifyName = "系统";
                break;
            case 107:
                classifyName = "生活";
                break;
            case 112:
                classifyName = "出行";
                break;
            case 118:
                classifyName = "安全";
                break;
            case 111:
                classifyName = "教育";
                break;
            case 109:
                classifyName = "健康";
                break;
            case 105:
                classifyName = "娱乐";
                break;
            case 100:
                classifyName = "儿童";
                break;
            case 113:
                classifyName = "办公";
                break;
            case 116:
                classifyName = "通讯";
                break;
        }
        return classifyName;
    }

    @RequestMapping("/appResult")
    public String appResult(int categoryId,
                            int pageSize,
                            int pageContext,
                            Model model){
        model.addAttribute("categoryId",categoryId);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("pageContext1",pageContext);
        return "app/appResult";
    }

    @RequestMapping("/appSearchResultJson")
    public @ResponseBody
    List<App> appSearchResultJson(String keyword){
        return appSearchService.searchAllAppsByKeyWord(keyword);
    }


    @RequestMapping("/appSearchResult")
    public String appSearchResult(String keyword, Model model){
        model.addAttribute("keyword",keyword);
        return "app/appSearchResult";
    }
}
