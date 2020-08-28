package application.service.feign.baiduyun;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wtl
 * @program: springboot
 * @description: BaiDuFeign接口类
 * @date 2020-03-02 16:39:11
 */
@FeignClient(name = "BaiDuFeign",url = "http://baiduyun.6miu.com")
public interface BaiDuFeign {

    /**
     * 获取搜索百度云资源的接口
     * @param keyword 关键词
     * @param pageIndex pageIndex
     * @return String
     */
    @GetMapping("/word-{keyword}-1-{pageIndex}.html")
    String getSearchPagingBaiduYunSources(@PathVariable("keyword") String keyword,
                                          @PathVariable("pageIndex") String pageIndex);

}
