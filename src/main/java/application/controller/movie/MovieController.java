package application.controller.movie;


import application.mybatis.model.IQiYi;
import application.service.iqiyi.IQiYiService;
import application.service.movie.GetAllMovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class MovieController {

    @Resource
    private GetAllMovieService getAllMovieService;

    @Resource
    private IQiYiService iQiYiService;

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
    List<IQiYi> selectByMovieLikeTitle(String keyword) {
        return iQiYiService.searchMovieResult(keyword, 0,Integer.MAX_VALUE);
    }
}
