package application.service.kuwo;

import application.filter.SysContext;
import application.model.kuwo.LeaderBoard;
import application.model.kuwo.PlayList;
import application.service.feign.kuwo.KowoFeign;
import application.service.feign.kuwo.MoBiFeign;
import application.service.feign.kuwo.SearchKuwoFeign;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import feign.Response;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Administrator
 * @title: KuwoService
 * @projectName OnlySearch
 * @description: TODO
 * @date 2020/6/615:05
 */
@Service
public class KuwoService {

    @Resource
    private KowoFeign kowoFeign;

    @Resource
    private SearchKuwoFeign searchKuwoFeign;

    @Resource
    private MoBiFeign moBiFeign;

    /**
     * 获取推荐歌单
     *
     * @param pageIndex 页码
     * @param pageSize  每页个数
     * @return String json
     */
    public String recommend(int pageIndex, int pageSize) {
        Response response = kowoFeign.playlistByHeader(pageIndex, pageSize, "");
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            System.out.println(cookie.split(";")[0]);
            result = kowoFeign.recommend(cookie.split(";")[0], pageIndex, pageSize);
            break;
        }
        return result;
    }

    /**
     * 获取歌单
     *
     * @param pageIndex 页码
     * @param pageSize  每页个数
     * @param id        id
     * @return String json
     */
    public String playlist(int pageIndex, int pageSize, String id) {
        Response response = kowoFeign.playlistByHeader(pageIndex, pageSize, "");
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.playlist(cookie.split(";")[0], pageIndex, pageSize, id);
            break;
        }
        return result;
    }

    /**
     * 排行榜
     * @param bangId 排行榜唯一id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    public String leaderBoard(
            String bangId,
            int pageIndex,
            int pageSize
    ){
        Response response = kowoFeign.recommendByHeader(pageIndex, pageSize);
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.leaderBoard(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],bangId, pageIndex, pageSize);
            break;
        }
        return result;
    }

    /**
     * 获取artist信息
     * @param category 分类
     * @param prefix 前缀
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    public String artistInfo(
            int category,
            String prefix,
            int pageIndex,
            int pageSize
    ){
        Response response = kowoFeign.artistInfoByHeader(category, prefix, pageIndex, pageSize);
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.artistInfo(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],category,prefix,pageIndex,pageSize);
            break;
        }
        return result;
    }

    /**
     * 获取歌曲信息
     * @param rid rid
     * @return String json
     */
    public String songInfo(String rid){
        Response response = kowoFeign.songInfoByHeader(rid,System.currentTimeMillis());
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.songInfo(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],rid,System.currentTimeMillis());
            break;
        }
        return result;
    }

    /**
     * 获取歌手的详情，包括songs、mv、专辑、个人介绍等
     * @param artistId 歌手id
     * @return String json
     */
    public String singerDetail(String artistId){
        Response response = kowoFeign.singerDetailByHeader(artistId);
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.singerDetail(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],artistId);
            break;
        }
        return result;
    }

    /**
     * 获取song单曲根据歌手
     * @param artistId 歌手id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    public String artistMusic(
            String artistId,
            int pageIndex,
            int pageSize
    ){
        Response response = kowoFeign.artistMusicByHeader(artistId,pageIndex,pageSize);
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.artistMusic(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],artistId,pageIndex,pageSize);
            break;
        }
        return result;
    }

    /**
     * 获取专辑根据歌手
     * @param artistId 歌手id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    public String artistAlbum(
            String artistId,
            int pageIndex,
            int pageSize
    ){
        Response response = kowoFeign.artistAlbumByHeader(artistId,pageIndex,pageSize);
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.artistAlbum(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],artistId,pageIndex,pageSize);
            break;
        }
        return result;
    }


    /**
     * 获取专辑歌曲列表根据专辑id
     * @param albumId 专辑id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    public String albumInfo(
            String albumId,
            int pageIndex,
            int pageSize
    ){
        Response response = kowoFeign.albumInfoByHeader(albumId,pageIndex,pageSize);
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.albumInfo(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],albumId,pageIndex,pageSize);
            break;
        }
        return result;
    }

    /**
     * 获取mv列表根据歌手id
     * @param artistId 歌手id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    public String artistMv(
            String artistId,
            int pageIndex,
            int pageSize
    ){
        Response response = kowoFeign.artistMvByHeader(artistId,pageIndex,pageSize);
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.artistMv(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],artistId,pageIndex,pageSize);
            break;
        }
        return result;
    }


    /**
     * 获取歌单信息根据歌单id
     * @param rid 歌单id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    public String musicInfo(
            String rid,
            int pageIndex,
            int pageSize
    ){
        Response response = kowoFeign.musicInfoByHeader(rid,pageIndex,pageSize);
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.musicInfo(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],rid,pageIndex,pageSize);
            break;
        }
        return result;
    }

    /**
     * 获取歌曲url根据歌单id
     * @param rid 歌单id
     * @return String json
     */
    public String url(String rid){
        Response response = kowoFeign.urlByHeader(rid,System.currentTimeMillis());
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.url(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],rid,System.currentTimeMillis());
            break;
        }
        return result;
    }

    /** 获取视频流
     * @param rid 歌手id
     * @param httpServletResponse response
     * @throws Exception Exception
     */
    public void getVideoStream(String rid,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) throws Exception {
        String url = url(rid);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //设置头信息
        URL ioUrl = new URL(url);
        long fSize = ioUrl.openConnection().getContentLength();
        InputStream inputStream = ioUrl.openConnection().getInputStream();
        httpServletResponse.setHeader("content-length",String.valueOf(fSize));
        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setHeader("Accept-Ranges", "bytes");
        long pos = 0;
        if (null != httpServletRequest.getHeader("Range")) {
            // 断点续传
            httpServletResponse.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            try {
                pos = Long.parseLong(httpServletRequest.getHeader("Range").replaceAll("bytes=", "").replaceAll("-", ""));
            } catch (NumberFormatException e) {
                pos = 0L;
            }
        }


        httpServletResponse.setHeader("Content-Range", "bytes " + pos + "-" + (fSize - 1) + "/" + fSize);
        inputStream.skip(pos);
        IOUtils.copy(inputStream,httpServletResponse.getOutputStream());
    }

    /**
     * 获取mv列表根据type id
     * @param pid type id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    public String mvList(String pid,int pageIndex,int pageSize){
        Response response = kowoFeign.mvListByHeader(pid,pageIndex,pageSize);
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.mvList(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],pid,pageIndex,pageSize);
            break;
        }
        return result;
    }

    /**
     * 获取歌单列表根据歌单id
     * @param pid 歌单id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    public String playListInfo(String pid,int pageIndex,int pageSize){
        Response response = kowoFeign.recommendByHeader(pageIndex,pageSize);
        Collection<String> cookies = response.headers().get("Set-Cookie");

        System.out.println(cookies);
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.playListInfo(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],pid,pageIndex,pageSize);
            break;
        }
        return result;
    }

    /**
     * 获取歌单列表根据typeId
     * @param id type id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    public String tagPlayList(String id,int pageIndex,int pageSize){
        Response response = kowoFeign.tagPlayListByHeader(id,pageIndex,pageSize);
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.tagPlayList(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],id,pageIndex,pageSize);
            break;
        }
        return result;
    }

    /**
     * 获取歌曲列表根据keyword
     * @param keyword 关键词
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    public String searchMusic(String keyword,int pageIndex,int pageSize){
        Response response = kowoFeign.searchKeyByHeader(keyword);
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.searchMusic(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],keyword,pageIndex,pageSize);
            break;
        }
        return result;
    }

    /**
     * 获取歌手列表根据keyword
     * @param keyword 关键词
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    public String searchArtist(String keyword,int pageIndex,int pageSize){
        Response response = kowoFeign.searchKeyByHeader(keyword);
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.searchArtist(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],keyword,pageIndex,pageSize);
            break;
        }
        return result;
    }

    /**
     * 获取专辑列表根据keyword
     * @param keyword 关键词
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    public String searchAlbum(String keyword,int pageIndex,int pageSize){
        Response response = kowoFeign.searchKeyByHeader(keyword);
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.searchAlbum(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],keyword,pageIndex,pageSize);
            break;
        }
        return result;
    }

    /**
     * 获取mv根据keyword
     * @param keyword 关键词
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    public String searchMv(String keyword,int pageIndex,int pageSize){
        Response response = kowoFeign.searchKeyByHeader(keyword);
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.searchMv(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],keyword,pageIndex,pageSize);
            break;
        }
        return result;
    }

    /**
     * 获取歌单根据keyword
     * @param keyword 关键词
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return String json
     */
    public String searchPlayList(String keyword,int pageIndex,int pageSize){
        Response response = kowoFeign.searchKeyByHeader(keyword);
        Collection<String> cookies = response.headers().get("Set-Cookie");
        String result = null;
        for (String cookie : cookies) {
            result = kowoFeign.searchPlayList(cookie.split(";")[0], cookie.split(";")[0].split("=")[1],keyword,pageIndex,pageSize);
            break;
        }
        return result;
    }

    /**
     * 获取专辑的列表
     * @param pageIndex 页码
     * @param pageSize 每页大小
     * @param ablumId ablum id
     * @return String
     */
    public String albums(int pageIndex,int pageSize,String ablumId){
        return searchKuwoFeign.albums(pageIndex, pageSize, ablumId);
    }

    /**
     * radio list
     * @return json String
     */
    public String radioList(){
        return moBiFeign.radioList();
    }

    /**
     * 转换data到List<PlayList>
     * @param data data String
     * @return List<PlayList>
     */
    public List<PlayList> covertDataToPlaylist(String data){
        List<PlayList> playLists = new ArrayList<>();
        JSONArray jsonArray = JSON.parseObject(data).getJSONObject("data").getJSONArray("data");
        for (int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String id = jsonObject.getString("id");
            String imgSrc = jsonObject.getString("img");
            String name = jsonObject.getString("name");
            playLists.add(PlayList.builder()
                    .id(id)
                    .imgSrc(imgSrc)
                    .name(name)
                    .build());
        }
        return playLists;
    }

    /**
     * 转换data到List<LeaderBoard>
     * @param data data String
     * @return List<LeaderBoard>
     */
    public List<LeaderBoard> covertDataToLeaderBoard(String data){
        List<LeaderBoard> leaderBoards = new ArrayList<>();
        JSONArray jsonArray = JSON.parseObject(data).getJSONObject("data").getJSONArray("musicList");
        for (int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int albumId = jsonObject.getInteger("albumid");
            String album = jsonObject.getString("album");
            String albumPic = jsonObject.getString("albumpic");
            String artist = jsonObject.getString("artist");
            int artistId = jsonObject.getInteger("artistid");
            int rid = jsonObject.getInteger("rid");
            String name = jsonObject.getString("name");
            String pic = jsonObject.getString("pic");
            String songTimeMinutes = jsonObject.getString("songTimeMinutes");
            leaderBoards.add(LeaderBoard.builder()
                    .albumId(albumId)
                    .album(album)
                    .albumPic(albumPic)
                    .artist(artist)
                    .artistId(artistId)
                    .rid(rid)
                    .name(name)
                    .pic(pic)
                    .songTimeMinutes(songTimeMinutes)
                    .build());
        }
        return leaderBoards;
    }
}
