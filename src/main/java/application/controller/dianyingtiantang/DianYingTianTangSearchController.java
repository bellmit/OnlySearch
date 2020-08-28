package application.controller.dianyingtiantang;

import application.model.dianyingtiantang.DownloadSource;
import application.model.dianyingtiantang.Information;
import application.service.dianyingtiantang.DianYingTianTangSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wtl
 */
@Controller
public class DianYingTianTangSearchController {

    @Autowired
    private DianYingTianTangSearchService dianYingTianTangSearchService;

    /**
     * 获得最新影片api
     *
     * @param pageIndex
     * @return
     */
    @GetMapping("/getPagingNewMovies")
    public @ResponseBody
    List<Information> getPagingNewMovies(String pageIndex) throws Exception {
        return dianYingTianTangSearchService.getPagingNewMovies(pageIndex);
    }


    /**
     * 获得国内电影api
     *
     * @param pageIndex
     * @return
     */
    @GetMapping("/getPagingChinaMovies")
    public @ResponseBody
    List<Information> getPagingChinaMovies(String pageIndex) throws Exception {
        return dianYingTianTangSearchService.getPagingChinaMovies(pageIndex);
    }

    /**
     * 获得欧美电影api
     *
     * @param pageIndex
     * @return
     */
    @GetMapping("/getPagingEuropeansMovies")
    public @ResponseBody
    List<Information> getPagingEuropeansMovies(String pageIndex) throws Exception {
        return dianYingTianTangSearchService.getPagingEuropeansMovies(pageIndex);
    }

    /**
     * 获得华语电视api
     *
     * @param pageIndex
     * @return
     */
    @GetMapping("/getPaginghuaYuTvs")
    public @ResponseBody
    List<Information> getPaginghuaYuTvs(String pageIndex) throws Exception {
        return dianYingTianTangSearchService.getPaginghuaYuTvs(pageIndex);
    }

    /**
     * 获得日韩电视api
     *
     * @param pageIndex
     * @return
     */
    @GetMapping("/getPagingRiHanTvs")
    public @ResponseBody
    List<Information> getPagingRiHanTvs(String pageIndex) throws Exception {
        return dianYingTianTangSearchService.getPagingRiHanTvs(pageIndex);
    }


    /**
     * 获得欧美电视api
     *
     * @param pageIndex
     * @return
     */
    @GetMapping("/getPagingOuMeiTvs")
    public @ResponseBody
    List<Information> getPagingOuMeiTvs(String pageIndex) throws Exception {
        return dianYingTianTangSearchService.getPagingOuMeiTvs(pageIndex);
    }


    /**
     * 获得最新综艺api
     *
     * @param pageIndex
     * @return
     */
    @GetMapping("/getPagingNewZongYis")
    public @ResponseBody
    List<Information> getPagingNewZongYis(String pageIndex) throws Exception {
        return dianYingTianTangSearchService.getPagingNewZongYis(pageIndex);
    }

    /**
     * 获得旧版综艺api
     *
     * @param pageIndex
     * @return
     */
    @GetMapping("/getPagingOldZongYis")
    public @ResponseBody
    List<Information> getPagingOldZongYis(String pageIndex) throws Exception {
        return dianYingTianTangSearchService.getPagingOldZongYis(pageIndex);
    }


    /**
     * 获得动漫api
     *
     * @param pageIndex
     * @return
     */
    @GetMapping("/getPagingDongMans")
    public @ResponseBody
    List<Information> getPagingDongMans(String pageIndex) throws Exception {
        return dianYingTianTangSearchService.getPagingDongMans(pageIndex);
    }


    /**
     * 获得游戏api
     *
     * @param pageIndex
     * @return
     */
    @GetMapping("/getPagingGames")
    public @ResponseBody
    List<Information> getPagingGames(String pageIndex) throws Exception {
        return dianYingTianTangSearchService.getPagingGames(pageIndex);
    }


    /**
     * 获得详情下载页api
     *
     * @param downloadPageUrl
     * @return
     */
    @GetMapping("/getDownloads")
    public @ResponseBody
    List<DownloadSource> getDownloads(String downloadPageUrl) throws Exception {
        return dianYingTianTangSearchService.getDownloads(downloadPageUrl);
    }


    @GetMapping("/getThunderPagingResult")
    public String getThunderPagingResult(String classify,
                                         String pageIndex,
                                         Model model) {
        model.addAttribute("classify", classify);
        model.addAttribute("pageIndex", pageIndex);
        return "thunder/thunderPagingResult";
    }

    @GetMapping("/getThunderDownloadResult")
    public String getThunderDownloadResult(String url,
                                           Model model) {
        model.addAttribute("url", url);
        return "thunder/thunderDownloadResult";
    }


    /**
     * 搜索分页结果api
     *
     * @param keyword
     * @param typeid
     * @param pageIndex
     * @return
     */
    @GetMapping("/getThunderSearchPagingResult")
    public @ResponseBody
    List<Information> getThunderSearchPagingResult(String keyword,
                                                   int typeid,
                                                   int pageIndex) {
        return dianYingTianTangSearchService.getSearchPagingResult(keyword, typeid, pageIndex);
    }

    /**
     * 搜索分页结果
     *
     * @param keyword
     * @param typeid
     * @param pageIndex
     * @return
     */
    @GetMapping("/getThunderResult")
    public String getThunderResult(String keyword,
                                   int typeid,
                                   int pageIndex,
                                   Model model) {
        model.addAttribute("keyword",keyword);
        model.addAttribute("typeid",typeid);
        model.addAttribute("pageIndex",pageIndex);
        return "thunder/thunderSearchResult";
    }

}
