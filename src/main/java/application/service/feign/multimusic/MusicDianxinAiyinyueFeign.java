package application.service.feign.multimusic;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 电信爱音乐
 */
@FeignClient(value = "MusicDianxinAiyinyueFeign", url = "https://www.imusic.cn")
public interface MusicDianxinAiyinyueFeign {
    /**
     * 根据keyword获取提示数据
     *
     * @param keyword
     * @return
     */
    @GetMapping("/imusic/hotWords.do?cmd=HotWordsDetailQuery&returnCount=10&singerCount=10&albumCount=2&Channel=WEB&Keyword={keyword}&queryType=1")
    String searchSuggest(@PathVariable("keyword") String keyword);

    @GetMapping(value = "//playlist/index/{offset}-{limit}.html",
            headers = {
                    "Accept=text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
                    "Accept-Language=zh-CN,zh;q=0.9",
                    "Cache-Control=no-cache",
                    "Connection=keep-alive",
                    "Host=www.imusic.cn",
                    "Pragma=no-cache",
                    "Referer=https://www.imusic.cn/",
                    "Upgrade-Insecure-Requests=1",
                    "User-Agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3732.400 QQBrowser/10.5.3819.400"
            })
    String songlist(@PathVariable("offset") long offset,
                    @PathVariable("limit") long limit);

    @GetMapping("//playlist/detail/{id}.html?pid={pid}")
    String getSongListInfo(@PathVariable("id") String id, @PathVariable("pid") String pid);

    @GetMapping("/s/index.do?param={keyword}&pid=2363_0_{pageIndex}_0")
    String searchSongsByKeyword(@PathVariable("keyword") String keyword,
                                @PathVariable("pageIndex") int pageIndex);
}
