package application.controller.tv;


import application.filter.SysContext;
import application.model.tv.CommonTvData;
import application.model.tv.TvCls;
import application.mybatis.model.IQiYi;
import application.mybatis.model.TenXun;
import application.service.iqiyi.IQiYiService;
import application.service.tengxun.TenXunService;
import application.service.tv.GetAllTvPlayService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class TVPlayController {

    @Resource
    private GetAllTvPlayService getAllTVPlayService;

    @Resource
    private IQiYiService iQiYiService;

    @Resource
    private TenXunService tenXunService;

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
    public @ResponseBody List<CommonTvData> selectByTvLikeTitle(String keyword) {
        List<TenXun> tenXuns = tenXunService.searchTvResult(keyword, 0, Integer.MAX_VALUE);
        List<IQiYi> iQiYis = iQiYiService.searchTvResult(keyword, 0, Integer.MAX_VALUE);
        List<CommonTvData> commonTvDataList = new ArrayList<>();
        tenXuns.forEach(tenXun -> {
            CommonTvData commonTvData = CommonTvData.builder()
                    .id(tenXun.getId())
                    .title(tenXun.getTitle())
                    .href(tenXun.getHref())
                    .imgSrc(tenXun.getImgSrc())
                    .jiNumber(tenXun.getJiNumber())
                    .channelId(tenXun.getChannelId())
                    .dataType(2)
                    .aid(SysContext.BLANK_STRING)
                    .json(SysContext.BLANK_STRING)
                    .stars(tenXun.getStars())
                    .build();
            commonTvDataList.add(commonTvData);
        });
        iQiYis.forEach(iQiYi -> {
            CommonTvData commonTvData = CommonTvData.builder()
                    .id(iQiYi.getId())
                    .title(iQiYi.getTitle())
                    .href(iQiYi.getHref())
                    .imgSrc(iQiYi.getImgSrc())
                    .jiNumber(iQiYi.getJiNumber())
                    .channelId(iQiYi.getChannelId())
                    .dataType(iQiYi.getDataType())
                    .aid(iQiYi.getAid())
                    .json(iQiYi.getJson())
                    .stars(iQiYi.getStars())
                    .build();
            commonTvDataList.add(commonTvData);
        });
        return commonTvDataList;
    }

    @CrossOrigin
    @GetMapping("/showTvMaps")
    public @ResponseBody
    Set<TvCls> showTvMaps(String url, String aid, String platform,String name) {
        return getAllTVPlayService.showTv(url, aid, platform,name);
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
