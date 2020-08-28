package application.service.feign.kuwo;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 * @title: KowoFeign
 * @projectName OnlySearch
 * @description: TODO
 * @date 2020/6/614:52
 */
@FeignClient(name = "KowoFeign",url = "https://www.kuwo.cn")
public interface KowoFeign {

    /**
     * 获取推荐列表
     * @param cookie cookie
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/api/pc/classify/playlist/getRcmPlayList?pn={pageIndex}&rn={pageSize}&order=new&httpsStatus=1")
    String recommend(
            @RequestHeader("Cookie") String cookie,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );

    /**
     * 获取歌单列表
     * @param cookie cookie
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @param id id
     * @return String json
     */
    @GetMapping("/api/pc/classify/playlist/getTagPlayList?pn={pageIndex}&rn={pageSize}&id={id}&httpsStatus=1")
    String playlist(
            @RequestHeader("Cookie") String cookie,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize,
            @PathVariable("id") String id
    );

    /**
     * 获取推荐列表header的cookie
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return Response
     */
    @RequestMapping(value = "/api/pc/classify/playlist/getRcmPlayList?pn={pageIndex}&rn={pageSize}&order=new&httpsStatus=1",method = RequestMethod.HEAD)
    Response recommendByHeader(
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );


    /**
     * 之请求头信息，获取cookie
     * @param pageIndex 页码
     * @param pageSize ，每页个数
     * @param id id
     * @return Response
     */
    @RequestMapping(value = "/api/pc/classify/playlist/getTagPlayList?pn={pageIndex}&rn={pageSize}&id={id}&httpsStatus=1",method = RequestMethod.HEAD)
    Response playlistByHeader(
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize,
            @PathVariable("id") String id
    );

    /**
     * 排行榜
     * @param cookie token
     * @param csrf csrf
     * @param bangId 排行榜唯一id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/api/www/bang/bang/musicList?bangId={bangId}&pn={pageIndex}&rn={pageSize}&httpsStatus=1")
    String leaderBoard(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable("bangId") String bangId,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );


    /**
     * 之请求头信息，获取cookie
     * @param category 分类
     * @param prefix 前缀
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return Response
     */
    @RequestMapping(value = "/api/www/artist/artistInfo?category={category}&prefix={prefix}&pn={pageIndex}&rn={pageSize}&httpsStatus=1",method = RequestMethod.HEAD)
    Response artistInfoByHeader(
            @PathVariable("category") int category,
            @PathVariable("prefix") String prefix,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );

    /**
     * 获取artist信息
     * @param cookie cookie
     * @param csrf csrf
     * @param category 分类
     * @param prefix 前缀
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/api/www/artist/artistInfo?category={category}&prefix={prefix}&pn={pageIndex}&rn={pageSize}&httpsStatus=1&reqId={reqId}")
    String artistInfo(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable("category") int category,
            @PathVariable("prefix") String prefix,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );

    /**
     * 获取歌曲信息
     * @param rid rid
     * @param timestamp 时间戳
     * @return String json
     */
    @RequestMapping(value = "/url?format=mp3&rid={rid}&response=url&type=convert_url3&br=128kmp3&from=web&t={timestamp}",method = RequestMethod.HEAD)
    Response songInfoByHeader(
            @PathVariable("rid") String rid,
            @PathVariable("timestamp") long timestamp);

    /**
     * 获取歌曲信息
     * @param cookie cookie
     * @param csrf csrf
     * @param rid rid
     * @param timestamp 时间戳
     * @return String json
     */
    @GetMapping("/url?format=mp3&rid={rid}&response=url&type=convert_url3&br=128kmp3&from=web&t={timestamp}")
    String songInfo(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable("rid") String rid,
            @PathVariable("timestamp") long timestamp);

    /**
     * 获取歌手的详情，包括songs、mv、专辑、个人介绍等
     * @param artistId 歌手id
     * @return Response
     */
    @RequestMapping(value = "/api/www/artist/artist?artistid={artistId}&httpsStatus=1",method = RequestMethod.HEAD)
    Response singerDetailByHeader(
            @PathVariable("artistId") String artistId
    );

    /**
     * 获取歌手的详情，包括songs、mv、专辑、个人介绍等
     * @param cookie cookie
     * @param csrf csrf
     * @param artistId 歌手id
     * @return String json
     */
    @GetMapping("/api/www/artist/artist?artistid={artistId}&httpsStatus=1")
    String singerDetail(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable("artistId") String artistId
    );


    /**
     * 获取song单曲根据歌手
     * @param artistId 歌手id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @RequestMapping(value = "/api/www/artist/artistMusic?artistid={artistId}&pn={pageIndex}&rn={pageSize}&httpsStatus=1",method = RequestMethod.HEAD)
    Response artistMusicByHeader(
            @PathVariable("artistId") String artistId,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );


    /**
     * 获取song单曲根据歌手
     * @param cookie cookie
     * @param csrf csrf
     * @param artistId 歌手id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/api/www/artist/artistMusic?artistid={artistId}&pn={pageIndex}&rn={pageSize}&httpsStatus=1")
    String artistMusic(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable("artistId") String artistId,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );

    /**
     * 获取专辑根据歌手
     * @param artistId 歌手id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @RequestMapping(value = "/api/www/artist/artistAlbum?artistid={artistId}&pn={pageIndex}&rn={pageSize}&httpsStatus=1",method = RequestMethod.HEAD)
    Response artistAlbumByHeader(
            @PathVariable("artistId") String artistId,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );


    /**
     * 获取专辑根据歌手
     * @param cookie cookie
     * @param csrf csrf
     * @param artistId 歌手id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/api/www/artist/artistAlbum?artistid={artistId}&pn={pageIndex}&rn={pageSize}&httpsStatus=1")
    String artistAlbum(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable("artistId") String artistId,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );


    /**
     * 获取专辑歌曲列表根据专辑id
     * @param albumId 专辑id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @RequestMapping(value = "/api/www/album/albumInfo?albumId={albumId}&pn={pageIndex}&rn={pageSize}&httpsStatus=1",method = RequestMethod.HEAD)
    Response albumInfoByHeader(
            @PathVariable("albumId") String albumId,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );


    /**
     * 获取专辑歌曲列表根据专辑id
     * @param cookie cookie
     * @param csrf csrf
     * @param albumId 专辑id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/api/www/album/albumInfo?albumId={albumId}&pn={pageIndex}&rn={pageSize}&httpsStatus=1")
    String albumInfo(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable("albumId") String albumId,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );


    /**
     * 获取mv列表根据歌手id
     * @param artistId 歌手id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @RequestMapping(value = "/api/www/artist/artistMv?artistid={artistId}&pn={pageIndex}&rn={pageSize}&httpsStatus=1",method = RequestMethod.HEAD)
    Response artistMvByHeader(
            @PathVariable("artistId") String artistId,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );


    /**
     * 获取mv列表根据歌手id
     * @param cookie cookie
     * @param csrf csrf
     * @param artistId 歌手id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/api/www/artist/artistMv?artistid={artistId}&pn={pageIndex}&rn={pageSize}&httpsStatus=1")
    String artistMv(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable("artistId") String artistId,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );

    /**
     * 获取歌曲url根据歌单id
     * @param rid 歌单id
     * @param timestamp 时间戳
     * @return String json
     */
    @RequestMapping(value = "/url?rid={rid}&response=url&format=mp4%7Cmkv&type=convert_url&t={timestamp}&httpsStatus=1",method = RequestMethod.HEAD)
    Response urlByHeader(
            @PathVariable("rid") String rid,
            @PathVariable("timestamp") long timestamp
    );

    /**
     * 获取歌曲url根据歌单id
     * @param cookie cookie
     * @param csrf csrf
     * @param rid 歌单id
     * @param timestamp 时间戳
     * @return String json
     */
    @GetMapping("/url?rid={rid}&response=url&format=mp4%7Cmkv&type=convert_url&t={timestamp}&httpsStatus=1")
    String url(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable("rid") String rid,
            @PathVariable("timestamp") long timestamp
    );


    /**
     * 获取mv列表根据type id
     * @param pid mv id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @RequestMapping(value = "/api/www/music/mvList?pid={pid}&pn={pageIndex}&rn={pageSize}&httpsStatus=1",method = RequestMethod.HEAD)
    Response mvListByHeader(
            @PathVariable("pid") String pid,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );


    /**
     * 获取mv列表根据type id
     * @param cookie cookie
     * @param csrf csrf
     * @param pid type id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/api/www/music/mvList?pid={pid}&pn={pageIndex}&rn={pageSize}&httpsStatus=1")
    String mvList(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable("pid") String pid,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );




    /**
     * 获取歌曲信息根据歌单id
     * @param rid 歌单id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @RequestMapping(value = "/api/www/music/musicInfo?mid={rid}&pn={pageIndex}&rn={pageSize}&httpsStatus=1",method = RequestMethod.HEAD)
    Response musicInfoByHeader(
            @PathVariable("rid") String rid,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );


    /**
     * 获取歌单信息根据歌单id
     * @param cookie cookie
     * @param csrf csrf
     * @param rid 歌单id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/api/www/music/musicInfo?mid={rid}&pn={pageIndex}&rn={pageSize}&httpsStatus=1")
    String musicInfo(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable("rid") String rid,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );

    /**
     * 获取歌曲列表根据歌单id
     * @param pid 歌单id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @RequestMapping(value = "/api/www/playlist/playListInfo?pid={pid}&pn={pageIndex}&rn={pageSize}&httpsStatus=1",method = RequestMethod.HEAD)
    Response playListInfoByHeader(
            @PathVariable("pid") String pid,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );


    /**
     * 获取歌单列表根据歌单id
     * @param cookie cookie
     * @param csrf csrf
     * @param pid 歌单id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/api/www/playlist/playListInfo?pid={pid}&pn={pageIndex}&rn={pageSize}&httpsStatus=1")
    String playListInfo(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable("pid") String pid,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );

    /**
     * 获取歌单列表根据typeId
     * @param id type id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @RequestMapping(value = "/api/pc/classify/playlist/getTagPlayList?id={id}&pn={pageIndex}&rn={pageSize}&httpsStatus=1",method = RequestMethod.HEAD)
    Response tagPlayListByHeader(
            @PathVariable("id") String id,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );


    /**
     * 获取歌单列表根据typeId
     * @param cookie cookie
     * @param csrf csrf
     * @param id type id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping("/api/pc/classify/playlist/getTagPlayList?id={id}&pn={pageIndex}&rn={pageSize}&httpsStatus=1")
    String tagPlayList(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable("id") String id,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );


    /**
     * head请求获取token和csrf
     * @param keyword 关键字
     * @return Response
     */
    @RequestMapping(value = "/search/list?key={keyword}",method = RequestMethod.HEAD)
    Response searchKeyByHeader(@PathVariable String keyword);

    /**
     * 获取歌曲列表根据keyword
     * @param cookie cookie
     * @param csrf csrf
     * @param keyword 关键词
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping(value = "/api/www/search/searchMusicBykeyWord?key={keyword}&pn={pageIndex}&rn={pageSize}&httpsStatus=1",headers = {
            "Referer=http://www.kuwo.cn/search/list?key="
    })
    String searchMusic(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable("keyword") String keyword,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("pageSize") int pageSize
    );

    /**
     * 获取歌手列表根据keyword
     * @param cookie cookie
     * @param csrf csrf
     * @param keyword 关键词
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping(value = "/api/www/search/searchArtistBykeyWord?key={keyword}&pn={pageIndex}&rn={pageSize}&httpsStatus=1",headers={
            "Referer=http://www.kuwo.cn/search/list?key="
    })
    String searchArtist(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable String keyword,
            @PathVariable int pageIndex,
            @PathVariable int pageSize
    );

    /**
     * 获取专辑列表根据keyword
     * @param cookie cookie
     * @param csrf csrf
     * @param keyword 关键词
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping(value = "/api/www/search/searchAlbumBykeyWord?key={keyword}&pn={pageIndex}&rn={pageSize}&httpsStatus=1",headers={
            "Referer=http://www.kuwo.cn/search/list?key="
    })
    String searchAlbum(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable String keyword,
            @PathVariable int pageIndex,
            @PathVariable int pageSize
    );

    /**
     * 获取mv根据keyword
     * @param cookie cookie
     * @param csrf csrf
     * @param keyword 关键词
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping(value = "/api/www/search/searchMvBykeyWord?key={keyword}&pn={pageIndex}&rn={pageSize}&httpsStatus=1",headers={
            "Referer=http://www.kuwo.cn/search/list?key="
    })
    String searchMv(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable String keyword,
            @PathVariable int pageIndex,
            @PathVariable int pageSize
    );

    /**
     * 获取歌单根据keyword
     * @param cookie cookie
     * @param csrf csrf
     * @param keyword 关键词
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    @GetMapping(value = "/api/www/search/searchPlayListBykeyWord?key={keyword}&pn={pageIndex}&rn={pageSize}&httpsStatus=1",headers = {
            "Referer=http://www.kuwo.cn/search/list?key="
    })
    String searchPlayList(
            @RequestHeader("Cookie") String cookie,
            @RequestHeader("csrf") String csrf,
            @PathVariable String keyword,
            @PathVariable int pageIndex,
            @PathVariable int pageSize
    );
}
