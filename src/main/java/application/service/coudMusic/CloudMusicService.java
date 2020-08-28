package application.service.coudMusic;

import application.filter.SysContext;
import application.model.cloudMusic.Song;
import application.model.cloudMusic.SongList;
import application.service.feign.cloudMusic.ApiImjadCnFeign;
import application.service.feign.cloudMusic.CloudMusicFeign;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: 网易云音乐service
 * @date 2020-04-04 12:55:01
 */
@Service
public class CloudMusicService {

    @Resource
    private CloudMusicFeign cloudMusicFeign;

    @Resource
    private ApiImjadCnFeign apiImjadCnFeign;

    public List<SongList> playlist(
            String cat,
            int pageIndex,
            int pageSize
    ) {
        List<SongList> songLists = new ArrayList<>();
        String html = cloudMusicFeign.playlist(cat, pageIndex * pageSize, pageSize);

        Document document = Jsoup.parse(html);

        Elements oLisElements = document.select("ul#m-pl-container li");
        for (Element element : oLisElements) {
            String imgSrc = element.selectFirst("div.u-cover.u-cover-1 img").attr("src");
            String title = element.selectFirst("div.u-cover.u-cover-1 a").attr("title");
            String playCount = element.selectFirst("div.bottom > span.nb").text();
            String playListId = element.selectFirst("div.bottom > a").attr("data-res-id");
            String author = element.selectFirst("p:nth-of-type(2)").text();
            songLists.add(
                    new SongList(
                            imgSrc, playCount, title, playListId, author
                    )
            );
        }
        return songLists;
    }

    /**
     * 根据playListId获取歌曲ids
     *
     * @param playListId 播单id
     * @return List<String>
     */
    public List<String> getPlaylistByPlayListId(String playListId) {
        List<String> songIds = new ArrayList<>();
        String json = cloudMusicFeign.getPlaylistByPlayListId(playListId);
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("tracks");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jo = jsonArray.getJSONObject(i);
            String songId = jo.getString("id");
            songIds.add(songId);
        }
        return songIds;
    }


    /**
     * 根据songId获取歌曲信息
     *
     * @param songId 音乐id
     * @return Song
     */
    public Song getSongInfoBySongId(String songId) {
        String songJson = apiImjadCnFeign.getSong(songId);
        String detailJson = cloudMusicFeign.detail(songId);
        JSONObject songJsonObject = JSONObject.fromObject(songJson);
        JSONObject detailJsonObject = JSONObject.fromObject(detailJson);
        JSONObject songData = songJsonObject.getJSONArray("data").getJSONObject(0);
        JSONObject detailData = detailJsonObject.getJSONArray("songs").getJSONObject(0);
        String name = SysContext.UNDEFINED_STRING.equalsIgnoreCase(detailData.getString("name")) ?
                detailData.getJSONArray("artists").getJSONObject(0).getString("name")
                : detailData.getString("name");
        String picUrl = detailData.getJSONObject("album").getString("picUrl");
        String songUrl = songData.getString("url");
        int size = songData.getInt("size");
        return new Song(
                songId, name, picUrl, songUrl, size
        );
    }

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
     * @param keyword 搜索关键词
     * @param pageIndex 偏移量
     * @param pageSize 数目
     * @return json String
     */
    public String search(
            String type,
            String keyword,
            int pageIndex,
            int pageSize
    ){
        return cloudMusicFeign.search(type, keyword, pageIndex, pageSize);
    }

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
    public List<Song> topN(String id){
        List<Song> songs = new ArrayList<>();
        String html = cloudMusicFeign.topN(id);
        String json = html.
                split("<textarea id=\"song-list-pre-data\" style=\"display:none;\">")[1].
                split("</textarea>")[0];

        JSONArray topNJsonArray = JSONArray.fromObject(json);
        for (int i=0;i<topNJsonArray.size();i++){
            JSONObject data = topNJsonArray.getJSONObject(i);
            String songId = data.getString("id");
            String name = data.getString("name");
            int size = -1;
            songs.add(
                    new Song(
                            songId,name,null,null,size
                    )
            );
        }
        return songs;
    }
}
