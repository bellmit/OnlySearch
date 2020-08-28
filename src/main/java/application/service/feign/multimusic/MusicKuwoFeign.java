package application.service.feign.multimusic;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 酷我音乐
 */
@FeignClient(value = "MusicKuwoFeign",url = "http://www.kuwo.cn")
public interface MusicKuwoFeign {
    @GetMapping("/api/pc/classify/playlist/getRcmPlayList?pn={pageIndex}&rn={limit}&order=new")
    String getSongLists(@PathVariable("pageIndex") long pageIndex, @PathVariable("limit") long limit);
}
