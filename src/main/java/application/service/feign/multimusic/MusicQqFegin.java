package application.service.feign.multimusic;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * QQ音乐
 */
@FeignClient(value = "MusicQqFeign",url = "https://c.y.qq.com")
public interface MusicQqFegin{
    /**
     * 根据keyword获取提示数据
     * @param keyword
     * @return
     */
    @GetMapping("/splcloud/fcgi-bin/smartbox_new.fcg?is_xml=0&key={keyword}&g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0")
    String searchSuggest(@PathVariable("keyword") String keyword);
}
