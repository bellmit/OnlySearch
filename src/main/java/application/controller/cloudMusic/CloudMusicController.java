package application.controller.cloudMusic;

import application.model.cloudMusic.Song;
import application.model.cloudMusic.SongList;
import application.service.coudMusic.CloudMusicService;
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
 * @description: 网易云音乐
 * @date 2020-04-04 12:14:43
 */
@Controller
@RequestMapping("cloudMusic")
public class CloudMusicController {

    @Resource
    private CloudMusicService cloudMusicService;

    @GetMapping("/playlist/{cat}/{pageIndex}/{pageSize}")
    public @ResponseBody
    List<SongList> playlist(
            @PathVariable("cat") String cat,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize) {
        return cloudMusicService.playlist(cat, pageIndex, pageSize);
    }

    @GetMapping("/getPlaylistByPlayListId")
    public @ResponseBody
    Object getPlaylistByPlayListId(String playListId) {
        return cloudMusicService.getPlaylistByPlayListId(playListId);
    }

    @GetMapping("/getSongInfoBySongId/{songId}")
    public @ResponseBody
    Song getSongInfoBySongId(
            @PathVariable("songId") String songId) {
        return cloudMusicService.getSongInfoBySongId(songId);
    }

    @GetMapping("/index")
    public String index() {
        return "cloudMusic/index";
    }

    @GetMapping("/songListPage/{playListId}")
    public String songListPage(@PathVariable("playListId") String playListId, Model model) {
        model.addAttribute("playListId", playListId);
        return "cloudMusic/songListPage";
    }


    @GetMapping("/search/{type}/{keyword}/{pageIndex}/{pageSize}")
    public @ResponseBody
    String search(
            @PathVariable("type") String type,
            @PathVariable("keyword") String keyword,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    ) {
        return cloudMusicService.search(type, keyword, pageIndex, pageSize);
    }

    @GetMapping("/searchResult/{keyword}")
    public String searchResult(@PathVariable("keyword") String keyword, Model model) {
        model.addAttribute("keyword", keyword);
        return "cloudMusic/songSearchResultPage";
    }

    @GetMapping("/topNIds/{id}")
    public @ResponseBody List<Song> topNIds(@PathVariable("id") String id){
        return cloudMusicService.topN(id);
    }

    @GetMapping("/topN/{id}")
    public String topN(@PathVariable("id") String id,Model model){
        model.addAttribute("id",id);
        return "cloudMusic/topN";
    }
}
