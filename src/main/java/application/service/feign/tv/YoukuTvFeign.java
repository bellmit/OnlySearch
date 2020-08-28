package application.service.feign.tv;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "YoukuTvFeign",url = "https://list.youku.com")
public interface YoukuTvFeign {

    @GetMapping("/category/page?c=97&type=show&p={pageIndex}")
    String searchTv(@PathVariable("pageIndex") int pageIndex);
}
