package application.service.feign.multimusic;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 1听音乐
 */
@FeignClient(value = "Music1tingFeign",url = "https://so.1ting.com")
public interface Music1tingFeign {

    /**
     * 根据keyword获取提示数据
     * @param keyword
     * @return
     */
    @GetMapping("/api/suggest/json?q={keyword}")
    String searchSuggest(@PathVariable("keyword") String keyword);

    @GetMapping("/song?q={keyword}&page={pageIndex}")
    String searchSongsByKeyword(@PathVariable("keyword") String keyword,
                                @PathVariable("pageIndex") int pageIndex);
}
