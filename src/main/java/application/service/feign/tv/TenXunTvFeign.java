package application.service.feign.tv;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wtl
 */
@FeignClient(value = "TenxunTvFeign",url = "https://v.qq.com")
public interface TenXunTvFeign {

    /**
     * 获取解析列表
     * @param pageIndex 偏移量
     * @param pageSize 页大小
     * @return String html
     */
    @GetMapping("/x/bu/pagesheet/list?append=1&channel=tv&feature=10471&listpage=2&offset={pageIndex}&pagesize={pageSize}&sort=-1")
    String searchTv(@PathVariable("pageIndex") int pageIndex,
                    @PathVariable("pageSize") int pageSize);
}
