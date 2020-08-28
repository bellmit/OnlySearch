package application.service.feign.multimusic;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 网易云音乐
 */
@FeignClient(value = "Music163Feign", url = "https://music.163.com")
public interface Music163Feign {
    @GetMapping("/discover/playlist/?order=hot&cat=%E5%85%A8%E9%83%A8&limit=35&offset={offset}")
    String songlist(@PathVariable("offset") long offset);


    @GetMapping("/playlist?id={id}")
    String getSongListInfo(@PathVariable("id") String id);
}
