package application.controller.youku;

import application.model.youku.YouKuVideo;
import application.service.youku.YouKuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: 优酷
 * @date 2020-03-30 20:11:25
 */
@Controller
@RequestMapping("/youku")
public class YouKuController {
    @Resource
    private YouKuService youKuService;

    @GetMapping("/index")
    public String index() {
        return "youku/index";
    }


    @GetMapping("/categoryList")
    public @ResponseBody
    String categoryList(
            int classify,
            String type,
            String time,
            String area,
            String videoType,
            int pageIndex,
            String stamp,
            String language
    ) {
        return youKuService.categoryList(classify, type, time, area, videoType, pageIndex, stamp, language);
    }

    @GetMapping("/videoList/{videoId}")
    public @ResponseBody
    List<YouKuVideo> videoList(@PathVariable("videoId") String videoId) throws Exception {
        return youKuService.videoList(videoId);
    }

    @GetMapping("/showVideo/{videoId}/{videoName}")
    public String showVideo(
            @PathVariable("videoId") String videoId,
            @PathVariable("videoName") String videoName,
            Model model) {
        model.addAttribute("videoId", videoId);
        model.addAttribute("videoName", videoName);
        return "youku/showVideo";
    }
}
