package application.controller.movie;


import application.filter.SysContext;
import application.model.tv.CommonTvData;
import application.mybatis.model.IQiYi;
import application.mybatis.model.TenXun;
import application.service.iqiyi.IQiYiService;
import application.service.movie.GetAllMovieService;
import application.service.tengxun.TenXunService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MovieController {

    @Resource
    private GetAllMovieService getAllMovieService;

    @Resource
    private IQiYiService iQiYiService;

    @Resource
    private TenXunService tenXunService;

    /**
     * 获得电影分页列表api
     * @param pageIndex
     * @param model
     * @return
     */
    @RequestMapping("/moviePlayResult")
    public @ResponseBody
    Map<String,Object> moviePlayResult(int pageIndex, Model model){
        return getAllMovieService.getAllResults(pageIndex);
    }

    @RequestMapping("/movieResult")
    public String movieResult(int pageIndex, Model model){
        model.addAttribute("pageIndex",pageIndex);
        return "movie/movieResult";
    }


    /**
     * 搜索电视剧结果
     *
     * @param keyword
     * @return
     */
    @GetMapping("/movieSearchResult")
    public String movieSearchResult(String keyword, Model model) {
        model.addAttribute("keyword", keyword);
        return "movie/movieSearchResult";
    }

    @GetMapping("/selectByMovieLikeTitle")
    public @ResponseBody
    List<CommonTvData> selectByMovieLikeTitle(String keyword) {
        List<CommonTvData> commonTvDataList = new ArrayList<>();
        List<IQiYi> iQiYis = iQiYiService.searchMovieResult(keyword, 0, Integer.MAX_VALUE);
        List<TenXun> tenXuns = tenXunService.searchMovieResult(keyword, 0, Integer.MAX_VALUE);
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
}
