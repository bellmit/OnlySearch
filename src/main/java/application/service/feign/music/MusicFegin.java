package application.service.feign.music;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MusicFegin",url = "http://www.kuwo.cn")
public interface MusicFegin {

    /**
     * 获取头部的cookie
     * @param key
     * @return
     */
    @RequestMapping(value = "/search/list?key={key}",method = RequestMethod.HEAD)
    Response search_list(@PathVariable("key") String key);

    /**
     * 根据关键字key查询信息列表
     * @param key
     * @param pn
     * @param rn
     * @param cookie
     * @param csrf
     * @param referer
     * @param userAgent
     * @return
     */
    @GetMapping("/api/www/search/searchMusicBykeyWord?key={key}&pn={pn}&rn={rn}")
    String searchMusicByKeyWord(@PathVariable("key") String key, @PathVariable("pn") int pn, @PathVariable("rn") int rn,
                                @RequestHeader("Cookie") String cookie,
                                @RequestHeader("csrf") String csrf,
                                @RequestHeader("Referer") String referer,
                                @RequestHeader("User-Agent") String userAgent);

    @GetMapping("url?format=mp3&rid={rid}&response=url&type=convert_url3&br=128kmp3&from=web")
    String getMusicUrl(@PathVariable("rid") String rid);
}
