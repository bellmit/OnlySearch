package application.service.feign.bilibili;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wtl
 * @program: springboot
 * @description: 哔哩哔哩_v2（Bilibili_v2）
 * @date 2020-03-04 06:56:01
 */
@FeignClient(name = "ApiImjadCnFeign",url = "https://api.imjad.cn/bilibili/v2")
public interface ApiImjadCnFeign {

    /**
     * @param type 排行榜类型
     * @param content 排行榜内容
     * @param duration 日期限制
     * @param New bool 是否为近期投稿
     * @return json String
     */
    @GetMapping("/?get=rank&type={type}&content={content}&duration={duration}&new={New}")
    String rank(@PathVariable("type") String type,
                @PathVariable("content") String content,
                @PathVariable("duration") int duration,
                @PathVariable("New") boolean New);

    /**
     * 搜索内容
     * @param keyword 关键词
     * @param pageIndex 页码
     * @return json String
     */
    @GetMapping("/?get=search&type=search&keyword={keyword}&page={pageIndex}")
    String search(@PathVariable("keyword") String keyword,
                  @PathVariable("pageIndex") int pageIndex);

}
