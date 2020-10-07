package application.controller.iqiyi;

import application.mybatis.model.IQiYi;
import application.service.iqiyi.IQiYiService;
import net.sf.json.JSONObject;
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
 * @program: springboot
 * @description:
 * @date 2020-03-13 18:12:08
 */
@Controller
@RequestMapping("/iqiyi")
public class IQiYiController {

    @Resource
    private IQiYiService iQiYiService;

    @GetMapping("/searchTv")
    public @ResponseBody
    Object searchTv(
            String channelId,
            String dataType,
            String finished,
            String marketReleaseDateLevel,
            String mode,
            String pageNumber,
            String pageSize,
            String area,
            String type,
            String version,
            String other,
            String other2,
            String other3
    ) {
        return iQiYiService.searchTv(channelId, dataType, finished, marketReleaseDateLevel, mode, pageNumber, pageSize, area, type, version, other, other2, other3);
    }

    /**
     * 展示页面
     *
     * @return
     */
    @GetMapping("/showContent")
    public String showContent() {
        return "iqiyi/index";
    }

    @GetMapping("/showTv")
    public @ResponseBody
    Object showTv(String aid,
                  int pageIndex,
                  int pageSize) {
        return iQiYiService.showTv(aid, pageIndex, pageSize);
    }

    @GetMapping("/show")
    public String show(String tVid, Model model) {
        String json = iQiYiService.getAid(tVid);
        model.addAttribute("aid", JSONObject.fromObject(json).getJSONObject("data").getString("aid"));
        return "computeXiGuaVideoUrl.html";
    }

    @GetMapping("/searchResult/{keyword}/{offset}/{size}")
    public @ResponseBody List<IQiYi> searchResult(
            @PathVariable("keyword") String keyword,
            @PathVariable("offset") int offset,
            @PathVariable("size") int size
    ) {
        return iQiYiService.searchResult(keyword, offset, size);
    }

    @GetMapping("/searchPage/{keyword}")
    public String searchPage(@PathVariable("keyword") String keyword,Model model){
        model.addAttribute("keyword",keyword);
        return "iqiyi/searchPage";
    }
}
