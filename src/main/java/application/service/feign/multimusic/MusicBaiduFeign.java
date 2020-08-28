package application.service.feign.multimusic;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 百度音乐
 */
@FeignClient(value = "MusicBaiduFeign",url = "http://sug.qianqian.com")
public interface MusicBaiduFeign{

    /**
     * 根据keyword获取提示数据
     * @param keyword
     * @return
     */
    @GetMapping("/info/suggestion?format=json&word={keyword}&version=2&from=web")
    String searchSuggest(@PathVariable("keyword") String keyword);
}
