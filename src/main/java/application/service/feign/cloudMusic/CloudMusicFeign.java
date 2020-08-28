package application.service.feign.cloudMusic;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: 网易云音乐Feign
 * @date 2020-04-04 12:50:14
 */
@FeignClient(name = "CloudMusicFeign",url = "https://music.163.com")
public interface CloudMusicFeign {

    /**
     * 根据plsylistId获取所有歌单
     * @param playListId
     * @return
     */
    @GetMapping("/api/playlist/detail?id={playListId}")
    String getPlaylistByPlayListId(
            @PathVariable("playListId") String playListId);


    /**
     * 获取歌曲详情
     * @param songId
     * @return
     */
    @GetMapping("/api/song/detail/?id={songId}&ids=[{songId}]")
    String detail(@PathVariable("songId") String songId);

    /**
     * 歌单列表
     * @param cat 分类
     * @param offset 偏移量
     * @param limit 数目
     * @return html String
     */
    @GetMapping("/discover/playlist/?order=hot&cat={cat}&limit={limit}&offset={offset}")
    String playlist(
            @PathVariable("cat") String cat,
            @PathVariable("offset") int offset,
            @PathVariable("limit") int limit
    );


    /**
     * 搜索相关内容
     * @param type 搜索类型
     * 1	单曲
     * 10	专辑
     * 100	歌手
     * 1000	歌单
     * 1002	用户
     * 1004	mv
     * 1006	歌词
     * 1009	主播电台
     * @param s 搜索关键词
     * @param offset 偏移量
     * @param limit 数目
     * @return json String
     */
    @PostMapping("/api/search/get/")
    String search(
            @RequestParam("type") String type,
            @RequestParam("s") String s,
            @RequestParam("offset") int offset,
            @RequestParam("limit") int limit
    );


    /**
     * 获取topN
     * @param id id
     * 19723756 云音乐飙升榜
     * 3779629 云音乐新歌榜
     * 2884035 网易原创歌曲榜
     * 3778678 云音乐热歌榜
     * 991319590 云音乐说唱榜
     * 71384707 云音乐古典音乐榜
     * 1978921795 云音乐电音榜
     * 2250011882 抖音排行榜
     * 2617766278 新声榜
     * 71385702 云音乐ACG音乐榜
     * 745956260 云音乐韩语榜
     * 10520166 云音乐国电榜
     * 2023401535 英国Q杂志中文版周榜
     * 2006508653 电竞音乐榜
     * 180106 UK排行榜周榜
     * 60198 美国Billboard周榜
     * 3812895 Beatport全球电子舞曲榜
     * 21845217 KTV唛榜
     * 11641012 iTunes榜
     * 60131 日本Oricon周榜
     * 120001 Hit FM Top榜
     * 112463 台湾Hito排行榜
     * 2809513713 云音乐欧美热歌榜
     * 2809577409 云音乐欧美新歌榜
     * 27135204 法国 NRJ Vos Hits 周榜
     * 3112516681 中国新乡村音乐排行榜
     * @return html String
     */
    @GetMapping("/discover/toplist?id={id}")
    String topN(@PathVariable("id") String id);

}
