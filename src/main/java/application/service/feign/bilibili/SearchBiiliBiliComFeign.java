package application.service.feign.bilibili;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wtl
 * @program: springboot
 * @description: 搜索视频的接口
 * @date 2020-03-04 12:59:38
 */
@FeignClient(name = "SearchBiiliBiliComFeign",url = "https://search.bilibili.com")
public interface SearchBiiliBiliComFeign {

    /**
     * 搜索功能
     * @param keyword 关键词
     * @param order 排序
     * @param duration 时间段
     * @param tids tids
     * @return html
     */
    @GetMapping(value = "/all?keyword={keyword}&order={order}&duration={duration}&tids_1={tids}&page={pageIndex}",
    headers = {
            "Cookie=main_confirmation=76cdbVS/JXKJLsrlCPEz9l1t/eE2/StcZpi1VYRVBNk=",
            "Host=search.bilibili.com",
            "User-Agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3732.400 QQBrowser/10.5.3819.400"
    })
    String search(@PathVariable("keyword") String keyword,
                  @PathVariable("order") String order,
                  @PathVariable("duration") String duration,
                  @PathVariable("tids") String tids,
                  @PathVariable("pageIndex") int pageIndex);

}
