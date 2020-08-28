package application.service.feign.multimusic;

import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "MusicBaiduGetListFeign", url = "http://music.taihe.com")
public interface MusicBaiduGetListFeign {
    @GetMapping(value = "/songlist/tag/%E5%85%A8%E9%83%A8?orderType=1&offset={offset}&third_type=",
            headers = {
                    "Accept=text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
                    "Accept-Language=zh-CN,zh;q=0.9",
                    "Cache-Control=no-cache",
                    "Connection=keep-alive",
                    "Host=music.taihe.com",
                    "Pragma=no-cache",
                    "Referer=http://music.taihe.com/songlist/tag/%E5%85%A8%E9%83%A8?orderType=1&offset=20&third_type=",
                    "Upgrade-Insecure-Requests=1",
                    "User-Agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3732.400 QQBrowser/10.5.3819.400"
            })
    String songlist(@PathVariable("offset") long offset);

    @GetMapping(value = "/songlist/{id}",headers = {
            "Accept=text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
            "Accept-Language=zh-CN,zh;q=0.9",
            "Cache-Control=no-cache",
            "Connection=keep-alive",
            "Host=music.taihe.com",
            "Pragma=no-cache",
            "Referer=http://music.taihe.com/songlist/tag/%E5%85%A8%E9%83%A8?orderType=1&offset=20&third_type=",
            "Upgrade-Insecure-Requests=1",
            "User-Agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3732.400 QQBrowser/10.5.3819.400"
    })
    String getSongListInfo(@PathVariable("id") String id);

    @GetMapping(value = "/search/song?s=1&key={keyword}&jump=0&start={start}&size=20&third_type=0",
    headers = {
            "Referer=http://music.taihe.com/search/song?s=1&key={keyword}&jump=0&start=0&size=20&third_type=0"
    })
    String searchSongsByKeyword(@PathVariable("keyword") @Param("keyword") String keyword, @PathVariable("start") int start);
}
