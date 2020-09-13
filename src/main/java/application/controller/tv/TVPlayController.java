package application.controller.tv;


import application.model.tv.TvCls;
import application.mybatis.model.IQiYi;
import application.service.iqiyi.IQiYiService;
import application.service.tv.GetAllTvPlayService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class TVPlayController {

    @Resource
    private GetAllTvPlayService getAllTVPlayService;

    @Resource
    private IQiYiService iQiYiService;

    @CrossOrigin
    @RequestMapping("tvPlayResult")
    public @ResponseBody
    Map<String, Object> tvPlayResult(int pageIndex, Model model) {
        return getAllTVPlayService.getAllResults(pageIndex);
    }

    @RequestMapping("/tvResult")
    public String tvResult(int pageIndex, Model model) {
        model.addAttribute("pageIndex", pageIndex);
        return "tv/tvResult";
    }

    /**
     * 搜索电视剧结果
     *
     * @param keyword
     * @return
     */
    @GetMapping("/tvSearchResult")
    public String tvSearchResult(String keyword, Model model) {
        model.addAttribute("keyword", keyword);
        return "tv/tvSearchPage";
    }

    @GetMapping("/selectByTvLikeTitle")
    public @ResponseBody List<IQiYi> selectByTvLikeTitle(String keyword) {
        return iQiYiService.searchTvResult(keyword, 0,Integer.MAX_VALUE);
    }

    @CrossOrigin
    @GetMapping("/showTvMaps")
    public @ResponseBody
    Set<TvCls> showTvMaps(String url, String aid, String platform) {
        return getAllTVPlayService.showTv(url, aid, platform);
    }

    @GetMapping("/showTv")
    public String showTv(String url, String aid, String platform, String tvName, Model model) {
        model.addAttribute("url", url);
        model.addAttribute("aid", aid);
        model.addAttribute("platform", platform);
        model.addAttribute("tvName", tvName);
        return "tv/showTv";
    }
}
