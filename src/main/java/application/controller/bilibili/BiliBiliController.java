package application.controller.bilibili;

import application.filter.SysContext;
import application.service.bilibili.BiliBiliIndexService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 哔哩哔哩首页
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/bilibili")
public class BiliBiliController {

    @Autowired
    private BiliBiliIndexService biliBiliIndexService;

    @GetMapping("/index")
    public String index() {
        return "bilibili/index";
    }

    /**
     * 获取url页面的所有热门标签
     *
     * @param url url页面
     * @return 所有热门标签
     */
    @GetMapping("/getHotTag")
    public @ResponseBody
    List<List<String>> getHotTag(String url) throws Exception {
        return biliBiliIndexService.getHotTag(url);
    }

    /**
     * 获取rid的video
     *
     * @param rid       rid类别
     * @param pageIndex 页码
     * @return Map<String, Object> 结果列表
     */
    @GetMapping("/getRidVideo")
    public @ResponseBody
    Map<String, Object> getRidVideo(String rid, int pageIndex) {
        return biliBiliIndexService.getRidVideo(rid, pageIndex);
    }

    /**
     * 单独获取动态video
     *
     * @param rid       rid
     * @param pageIndex 页码
     * @return Object
     */
    @GetMapping("/getDynamicList")
    public @ResponseBody
    Object getDynamicList(String rid, int pageIndex) {
        return biliBiliIndexService.getDynamicList(rid, pageIndex);
    }

    /**
     * 获取video列表
     *
     * @param rid       rid
     * @param pageIndex 页码
     * @return Object
     */
    @GetMapping("/getNewList")
    public @ResponseBody
    Object getNewList(String rid, int pageIndex) {
        return biliBiliIndexService.getNewList(rid, pageIndex);
    }

    /**
     * 根据tag_id获取video列表
     *
     * @param rid       rid
     * @param tagId     tag_id
     * @param pageIndex 页码
     * @return 返回的video列表
     */
    @GetMapping("/getNewListByTagId")
    public @ResponseBody
    Object getNewListByTagId(String rid, int tagId, int pageIndex) {
        return biliBiliIndexService.getNewListByTagId(rid, tagId, pageIndex);
    }

    /**
     * 根据tag_id单独获取动态video
     *
     * @param rid       rid
     * @param tagId     tag_id
     * @param pageIndex 页码
     * @return 单独获取动态video
     */
    @GetMapping("/getDynamicListByTagId")
    public @ResponseBody
    Object getDynamicListByTagId(String rid, int tagId, int pageIndex) {
        return biliBiliIndexService.getDynamicListByTagId(rid, tagId, pageIndex);
    }

    /**
     * 获取rid的video
     *
     * @param rid       rid类别
     * @param pageIndex 页码
     * @return Map<String, Object> 结果列表
     */
    @GetMapping("/getRidVideoByTagId")
    public @ResponseBody
    Map<String, Object> getRidVideoByTagId(String rid, int tagId, int pageIndex) {
        return biliBiliIndexService.getRidVideoByTagId(rid, tagId, pageIndex);
    }

    /**
     * 获取哔哩哔哩的videoPage的所有cid的信息
     *
     * @param aid aid
     * @return Object
     */
    @GetMapping("/getBiliBiliVideoPageCids/{aid}")
    public @ResponseBody
    Object getBiliBiliVideoPageCids(@PathVariable("aid") String aid) {
        return biliBiliIndexService.getBiliBiliVideoPageCids(aid);
    }

    /**
     * 获取哔哩哔哩的video的信息
     *
     * @param aid aid
     * @param cid cid
     * @return String
     * @throws Exception 抛出的异常
     */
    @GetMapping("/getBiliBiliVideoUrl/{aid}/{cid}")
    public @ResponseBody
    String getBiliBiliVideoUrl(@PathVariable("aid") String aid,
                               @PathVariable("cid") String cid) throws Exception {
        return biliBiliIndexService.getBiliBiliVideoUrl(aid, cid);
    }

    /**
     * 展示哔哩哔哩的video
     *
     * @param bVid   bVid
     * @param model model
     * @return String
     */
    @GetMapping("/redirectVideoPage/{bVid}/{cid}/{aid}")
    public String redirectVideoPage(@PathVariable String bVid,
                                    @PathVariable String cid,
                                    @PathVariable String aid,
                                    Model model) {
        model.addAttribute("bVid", bVid);
        model.addAttribute("cid",cid);
        model.addAttribute("aid",aid);
        return "bilibili/redirectVideoPage";
    }

    /**
     * 获取视频信息
     *
     * @param aid       aid
     * @param pageIndex 页码
     * @return json String
     */
    @GetMapping("/getVideoInfo/{aid}/{pageIndex}")
    public @ResponseBody
    Object getVideoInfo(@PathVariable("aid") String aid,
                        @PathVariable("pageIndex") int pageIndex) {
        return biliBiliIndexService.getVideoInfo(SysContext.BILIBILI_APPKEY, aid, pageIndex);
    }

    @GetMapping("/rank/{type}/{content}/{duration}/{New}")
    public @ResponseBody
    String rank(@PathVariable("type") String type,
                @PathVariable("content") String content,
                @PathVariable("duration") int duration,
                @PathVariable("New") boolean New) {
        return biliBiliIndexService.rank(type, content, duration, New);
    }

    @GetMapping("/ranking")
    public String ranking() {
        return "bilibili/ranking";
    }

    /**
     * search搜索页面的渲染
     *
     * @return String 返回搜索页面
     */
    @GetMapping("/searchPage")
    public String searchPage(String keyword, Model model) {
        model.addAttribute("keyword", keyword);
        return "bilibili/searchPage";
    }

    @GetMapping("/searchResult")
    public @ResponseBody
    Map<String, Object> searchResult(String keyword,
                                     String order,
                                     String duration,
                                     String tids_1,
                                     int pageIndex) {
        return biliBiliIndexService.searchResult(keyword, order, duration, tids_1, pageIndex);
    }

    @GetMapping("/downloadVideos/{bVid}/{aid}")
    public String downloadVideos(@PathVariable String bVid,
                                 @PathVariable String aid,
                                 Model model){
        model.addAttribute("bVid",bVid);
        model.addAttribute("aid",aid);
        return "bilibili/downloadVideo";
    }

    /**
     * 获取该aid的video的所有的page
     * @param bVid
     * @return String
     */
    @GetMapping("/getBiliBiliVideoPageBVids/{bVid}")
    public @ResponseBody Object getBiliBiliVideoPageBVids(@PathVariable("bVid") String bVid){
        return biliBiliIndexService.getBiliBiliVideoPageBVids(bVid);
    }
}
