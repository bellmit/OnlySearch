package application.controller.xiguavideo;

import application.service.xiguavideo.XiGuaVideoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: wtl
 * @License: (C) Copyright 2020, wtl Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020-10-06 19:25
 * @Version: 1.0
 * @Description:
 */
@Controller
@RequestMapping("/xiGua")
public class XiGuaVideoController {

    @Resource
    private XiGuaVideoService xiGuaVideoService;

    @GetMapping("/getXiGuaVideoUrl")
    public @ResponseBody
    List<Map<String,Object>> getXiGuaVideoUrl(String xiGuaUrl) throws Exception {
        return xiGuaVideoService.getVideoUrl(xiGuaUrl);
    }

    /**
     * 显示videoUrl
     * @param xiGuaUrl 西瓜url
     * @param model model
     * @return String html
     */
    @GetMapping("/showVideoUrl")
    public String showVideoUrl(String xiGuaUrl, Model model){
        model.addAttribute("xiGuaUrl",xiGuaUrl);
        return "xiguavideo/showVideo";
    }

    /**
     * 计算页
     * @return String redrict page
     */
    @GetMapping("/computeXiGuaVideoUrl")
    public String computeXiGuaVideoUrl(){
        return "xiguavideo/computeXiGuaVideoUrl";
    }

    /**
     * 获取详细的电视或电影的剧集列表和推荐列表
     * @param albumId album id
     * @return String json
     */
    @GetMapping("/detailPagesTvOrMovie/{albumId}")
    public @ResponseBody String detailPagesTvOrMovie(@PathVariable String albumId){
        xiGuaVideoService.detailPagesTvOrMovie(albumId);
        return null;
    }
}
