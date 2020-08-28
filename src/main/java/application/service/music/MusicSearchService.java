package application.service.music;

import application.model.music.Music;
import application.service.feign.music.MusicFegin;
import feign.Response;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MusicSearchService {

    @Autowired
    private MusicFegin musicFegin;

    public List<Music> getAllMatchingMusics(String keyword,
                                            int pageIndex,
                                            int pageSize) throws Exception {
        List<Music> musics = new ArrayList<Music>();

        Response response = musicFegin.search_list("周杰伦");
        Collection<String> setCookies = response.headers().get("Set-Cookie");
        String kw_token = null;
        for (String setCookie : setCookies){
            if (setCookie.contains("kw_token")){
                kw_token = setCookie.split(";")[0].split("=")[1];
                System.out.println(setCookie);
                break;
            }
        }

        if (null != kw_token){
            System.out.println(kw_token);
            String json = musicFegin.searchMusicByKeyWord(keyword, pageIndex, pageSize, "kw_token=" + kw_token
                    ,kw_token,"http://www.kuwo.cn/search/list"
                    ,"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3732.400 QQBrowser/10.5.3819.400"
            );
            JSONObject jsonObject = JSONObject.fromObject(json)
                    .getJSONObject("data");
            int total = Integer.parseInt(jsonObject.getString("total"));
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (int i=0;i<jsonArray.size();i++){
                JSONObject object = jsonArray.getJSONObject(i);
                JSONObject musicUrlJson = JSONObject.fromObject(musicFegin.getMusicUrl(object.getString("rid")));
                musics.add(new Music(object.getString("rid")
                        ,object.getString("album")
                        ,object.containsKey("pic") ?
                        object.getString("pic") :
                        "/images/music/exchangeMusicsUrl.png"
                        ,object.getString("artist")
                        ,musicUrlJson.getString("url")
                        ,total));
            }
        }
        return musics;
    }
}
