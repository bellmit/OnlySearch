package application.controller.kuwo;

import application.service.kuwo.KuwoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import feign.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @title: IndexController
 * @projectName OnlySearch
 * @description: TODO
 * @date 2020/6/613:36
 */
@Controller
@RequestMapping("/kuwo")
public class KuwoController {

    @Resource
    private KuwoService kuwoService;

    @GetMapping("/index")
    public String index(Model model) {
        String recommend = kuwoService.recommend(1, 5);
        String playlistCover = kuwoService.playlist(1, 5, "1848");
        String playlistNet = kuwoService.playlist(1, 5, "621");
        String playlistSad = kuwoService.playlist(1, 5, "146");
        String playlistEurope = kuwoService.playlist(1, 5, "35");
        model.addAttribute("recommends", new Object[]{
                new Object[]{"每日推荐", kuwoService.covertDataToPlaylist(recommend)},
                new Object[]{"翻唱", kuwoService.covertDataToPlaylist(playlistCover)},
                new Object[]{"网络", kuwoService.covertDataToPlaylist(playlistNet)},
                new Object[]{"伤感", kuwoService.covertDataToPlaylist(playlistSad)},
                new Object[]{"欧美", kuwoService.covertDataToPlaylist(playlistEurope)},

        });

        String hotSongList = kuwoService.leaderBoard("158", 1, 5);
        String newSongList = kuwoService.leaderBoard("93", 1, 5);
        String upSongList = kuwoService.leaderBoard("279", 1, 5);
        String europeSongList = kuwoService.leaderBoard("290", 1, 5);
        String kuwoSongList = kuwoService.leaderBoard("17", 1, 5);
        model.addAttribute("leaderBoards", new Object[]{
                new Object[]{"抖音热歌榜", kuwoService.covertDataToLeaderBoard(hotSongList)},
                new Object[]{"酷我新歌榜", kuwoService.covertDataToLeaderBoard(newSongList)},
                new Object[]{"酷我飙升榜", kuwoService.covertDataToLeaderBoard(upSongList)},
                new Object[]{"ACG新歌榜", kuwoService.covertDataToLeaderBoard(europeSongList)},
                new Object[]{"酷我热歌榜", kuwoService.covertDataToLeaderBoard(kuwoSongList)},

        });

        return "kuwo/index";
    }

    /**
     * 获取推荐列表
     *
     * @param pageIndex 页码
     * @param pageSize  每页个数
     * @return String json
     */
    @GetMapping("/recommend/{pageIndex}/{pageSize}")
    public @ResponseBody
    String recommend(
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    ) {
        return kuwoService.recommend(pageIndex, pageSize);
    }

    /**
     * 获取歌单列表
     *
     * @param pageIndex 页码
     * @param pageSize  每页个数
     * @param id        id
     * @return String json
     */
    @GetMapping("/playlist/{pageIndex}/{pageSize}/{id}")
    public @ResponseBody
    String playlist(
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize,
            @PathVariable("id") String id
    ) {
        return kuwoService.playlist(pageIndex, pageSize, id);
    }


    /**
     * 排行榜
     *
     * @param bangId    排行榜唯一id
     * @param pageIndex 页码
     * @param pageSize  每页个数
     * @return String json
     */
    @GetMapping("/leaderBoard/{bangId}/{pageIndex}/{pageSize}")
    public @ResponseBody
    String leaderBoard(
            @PathVariable("bangId") String bangId,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    ) {
        return kuwoService.leaderBoard(bangId, pageIndex, pageSize);
    }

    /**
     * 排行榜
     *
     * @return String json
     */
    @GetMapping("/hotTop")
    public String hotTop() {
        return "kuwo/hotTop";
    }

    /**
     * 获取artist信息
     *
     * @param category  分类
     * @param prefix    前缀
     * @param pageIndex 页码
     * @param pageSize  每页个数
     * @return String json
     */
    @GetMapping("/artistInfo/{category}/{pageIndex}/{pageSize}")
    public @ResponseBody
    String artistInfo(
            @PathVariable("category") int category,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize,
            String prefix
    ) {
        return kuwoService.artistInfo(category, prefix, pageIndex, pageSize);
    }

    @GetMapping("/singer/{category}/{pageIndex}/{pageSize}/{otherSize}/{currentPage}")
    public String singer(
            @PathVariable("category") int category,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize,
            @PathVariable("otherSize") int otherSize,
            @PathVariable("currentPage") int currentPage,
            String prefix,
            Model model) {
        //热门列表
        int len = 26;
        char[] charList = new char[len];
        for (int i = 0; i < len; i++) {
            charList[i] = (char) (65 + i);
        }
        model.addAttribute("hotList", charList);
        //category分类
        String[] categoryList = new String[]{
                "华语男", "华语女", "华语组合", "日韩男", "日韩女", "日韩组合", "欧美男", "欧美女", "欧美组合", "其他"
        };
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("category", category);
        model.addAttribute("prefix", prefix);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("otherSize", otherSize);
        model.addAttribute("currentPage", currentPage);
        return "kuwo/singer";
    }

    /**
     * 获取歌曲信息
     *
     * @param rid rid
     * @return String json
     */
    @GetMapping("/songInfo/{rid}")
    public @ResponseBody
    String songInfo(@PathVariable("rid") String rid) {
        return kuwoService.songInfo(rid);
    }

    /**
     * 获取歌手的详情，包括songs、mv、专辑、个人介绍等
     *
     * @param artistId 歌手id
     * @return String json
     */
    @GetMapping("/singerDetail/{artistId}")
    public @ResponseBody
    String singerDetail(
            @PathVariable("artistId") String artistId) {
        return kuwoService.singerDetail(artistId);
    }

    /**
     * 获取歌手的详情，包括songs、mv、专辑、个人介绍等
     *
     * @param artistId 歌手id
     * @return String json
     */
    @GetMapping("/singerDetailInfo/{artistId}")
    public String singerDetailInfo(
            @PathVariable("artistId") String artistId,
            Model model) {
        model.addAttribute("artistId", artistId);
        return "kuwo/singerDetail";
    }

    /**
     * 获取song单曲根据歌手
     *
     * @param artistId  歌手id
     * @param pageIndex 页码
     * @param pageSize  每页个数
     * @return String json
     */
    @GetMapping("/artistMusic/{artistId}/{pageIndex}/{pageSize}")
    public @ResponseBody
    String artistMusic(
            @PathVariable("artistId") String artistId,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    ) {
        return kuwoService.artistMusic(artistId, pageIndex, pageSize);
    }

    /**
     * 获取专辑根据歌手
     *
     * @param artistId  歌手id
     * @param pageIndex 页码
     * @param pageSize  每页个数
     * @return String json
     */
    @GetMapping("/artistAlbum/{artistId}/{pageIndex}/{pageSize}")
    public @ResponseBody
    String artistAlbum(
            @PathVariable("artistId") String artistId,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    ) {
        return kuwoService.artistAlbum(artistId, pageIndex, pageSize);
    }

    /**
     * 获取专辑歌曲列表根据专辑id
     *
     * @param albumId   专辑id
     * @param pageIndex 页码
     * @param pageSize  每页个数
     * @return String json
     */
    @GetMapping("/albumInfo/{albumId}/{pageIndex}/{pageSize}")
    public @ResponseBody
    String albumInfo(
            @PathVariable("albumId") String albumId,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    ) {
        return kuwoService.albumInfo(albumId, pageIndex, pageSize);
    }


    /**
     * 获取专辑歌曲列表根据专辑id
     *
     * @param albumId 专辑id
     * @return String json
     */
    @GetMapping("/albumInfoPage/{albumId}")
    public String albumInfoPage(
            @PathVariable("albumId") String albumId,
            Model model
    ) {
        model.addAttribute("albumId", albumId);
        return "kuwo/albumInfo";
    }

    /**
     * 获取mv列表根据歌手id
     *
     * @param artistId  歌手id
     * @param pageIndex 页码
     * @param pageSize  每页个数
     * @return String json
     */
    @GetMapping("/artistMv/{artistId}/{pageIndex}/{pageSize}")
    public @ResponseBody
    String artistMv(
            @PathVariable("artistId") String artistId,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    ) {
        return kuwoService.artistMv(artistId, pageIndex, pageSize);
    }

    /**
     * 获取歌曲url根据歌单id
     *
     * @param rid 歌单id
     * @return String json
     */
    @GetMapping("/url/{rid}")
    public @ResponseBody
    String url(@PathVariable("rid") String rid) {
        return kuwoService.url(rid);
    }

    /**
     * artistMv页面
     *
     * @param rid 歌手id
     * @return String
     */
    @GetMapping("/artistMvPage/{rid}")
    public String artistMvPage(@PathVariable("rid") String rid, Model model) {
        model.addAttribute("rid", rid);
        return "kuwo/artistMv";
    }

    /**
     * 获取歌单信息根据歌单id
     *
     * @param rid       歌单id
     * @param pageIndex 页码
     * @param pageSize  每页个数
     * @return String json
     */
    @GetMapping("/musicInfo/{rid}/{pageIndex}/{pageSize}")
    public @ResponseBody
    String musicInfo(
            @PathVariable("rid") String rid,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    ) {
        return kuwoService.musicInfo(rid, pageIndex, pageSize);
    }

    /**
     * 获取mv列表根据type id
     * @param pid type id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/mvList/{pid}/{pageIndex}/{pageSize}")
    public @ResponseBody String mvList(
            @PathVariable("pid") String pid,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    ){
        return kuwoService.mvList(pid, pageIndex, pageSize);
    }


    /**
     * 获取mv列表根据type id
     * @param pid type id
     * @return String json
     */
    @GetMapping("/mvListPage/{pid}")
    public String mvListPage(
            @PathVariable("pid") String pid,
            Model model
    ){
        model.addAttribute("pid",pid);
        return "kuwo/mvList";
    }

    /**
     * 获取歌单列表根据歌单id
     * @param pid 歌单id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/playListInfo/{pid}/{pageIndex}/{pageSize}")
    public @ResponseBody String playListInfo(
            @PathVariable("pid") String pid,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    ){
        return kuwoService.playListInfo(pid, pageIndex, pageSize);
    }


    /**
     * 获取歌单列表根据歌单id
     * @param pid 歌单id
     * @return String
     */
    @GetMapping("/playListInfo/{pid}")
    public String playListInfoPage(
            @PathVariable("pid") String pid,
            Model model
    ){
        model.addAttribute("pid",pid);
        return "kuwo/playListInfo";
    }

    /**
     * 获取歌单列表根据typeId
     * @param id type id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/tagPlayList/{id}/{pageIndex}/{pageSize}")
    public @ResponseBody String tagPlayList(
            @PathVariable("id") String id,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    ){
        return kuwoService.tagPlayList(id, pageIndex, pageSize);
    }

    /**
     * 获取歌单列表根据typeId
     * @param id type id
     * @return String
     */
    @GetMapping("/tagPlayListPage/{id}")
    public String tagPlayListPage(
            @PathVariable("id") String id,
            Model model
    ){
        model.addAttribute("id",id);
        return "kuwo/tagPlayList";
    }

    /**
     * 获取歌曲列表根据keyword
     * @param keyword 关键词
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/searchMusic/{keyword}/{pageIndex}/{pageSize}")
    public @ResponseBody String searchMusic(
            @PathVariable String keyword,
            @PathVariable int pageIndex,
            @PathVariable int pageSize
    ){
        return kuwoService.searchMusic(keyword, pageIndex, pageSize);
    }

    /**
     * 获取歌手列表根据keyword
     * @param keyword 关键词
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/searchArtist/{keyword}/{pageIndex}/{pageSize}")
    public @ResponseBody String searchArtist(
            @PathVariable String keyword,
            @PathVariable int pageIndex,
            @PathVariable int pageSize
    ){
        return kuwoService.searchArtist(keyword, pageIndex, pageSize);
    }

    /**
     * 获取专辑列表根据keyword
     * @param keyword 关键词
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/searchAlbum/{keyword}/{pageIndex}/{pageSize}")
    public @ResponseBody String searchAlbum(
            @PathVariable String keyword,
            @PathVariable int pageIndex,
            @PathVariable int pageSize
    ){
        return kuwoService.searchAlbum(keyword, pageIndex, pageSize);
    }

    /**
     * 获取mv根据keyword
     * @param keyword 关键词
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/searchMv/{keyword}/{pageIndex}/{pageSize}")
    public @ResponseBody String searchMv(
            @PathVariable String keyword,
            @PathVariable int pageIndex,
            @PathVariable int pageSize
    ){
        return kuwoService.searchMv(keyword, pageIndex, pageSize);
    }

    /**
     * 获取歌单根据keyword
     * @param keyword 关键词
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/searchPlayList/{keyword}/{pageIndex}/{pageSize}")
    public @ResponseBody String searchPlayList(
            @PathVariable String keyword,
            @PathVariable int pageIndex,
            @PathVariable int pageSize
    ){
        return kuwoService.searchPlayList(keyword, pageIndex, pageSize);
    }

    @GetMapping("/searchPage/{keyword}")
    public String searchPage(@PathVariable String keyword,Model model){
        model.addAttribute("keyword",keyword);
        return "kuwo/searchPage";
    }

    /**
     * 获取专辑的列表
     * @param pageIndex 页码
     * @param pageSize 每页大小
     * @param ablumId ablum id
     * @return String
     */
    @GetMapping("albums/{ablumId}/{pageIndex}/{pageSize}")
    public String albums(
            @PathVariable String ablumId,
            @PathVariable int pageIndex,
            @PathVariable int pageSize
    ){
        return kuwoService.albums(pageIndex, pageSize, ablumId);
    }

    /**
     * radio list
     * @return json String
     */
    @GetMapping("/radioList")
    public @ResponseBody String radioList(){
        return kuwoService.radioList();
    }
}
