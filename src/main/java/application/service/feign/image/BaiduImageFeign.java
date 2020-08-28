package application.service.feign.image;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "BaiduImageFeign",url = "https://image.baidu.com")
public interface BaiduImageFeign {

    @GetMapping("/search/acjson?tn=resultjson_com&ipn=rj&queryWord={keyword}&ie=utf-8&oe=utf-8&word={keyword}&pn={pageIndex}&rn={pageSize}")
    String searchByKeyword(@PathVariable("keyword") String keyword,
                           @PathVariable("pageIndex") int pageIndex,
                           @PathVariable("pageSize") int pageSize);
}
