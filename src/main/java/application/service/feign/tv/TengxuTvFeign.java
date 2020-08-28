package application.service.feign.tv;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "TengxuTvFeign",url = "https://v.qq.com")
public interface TengxuTvFeign {

    @GetMapping("/x/bu/pagesheet/list?append=1&channel=tv&feature=10471&listpage=2&offset={pageIndex}&pagesize={pageSize}&sort=19")
    String searchTv(@PathVariable("pageIndex") int pageIndex,
                    @PathVariable("pageSize") int pageSize);
}
