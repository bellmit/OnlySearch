package application.service.feign.dianyingtiantang;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Administrator
 */
@FeignClient(value = "DianYingTianTangFeign", url = "https://www.ygdy8.com")
public interface DianYingTianTangFeign {

    @GetMapping(value = "/html/{content}/list_{type}_{pageIndex}.html",headers = {
            "Accept=*/*",
            "Cache-Control=no-cache",
            "Connection=keep-alive",
            "Host=www.ygdy8.com",
            "Pragma=no-cache",
            "User-Agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3732.400 QQBrowser/10.5.3819.400"
    })
    Response getPageingContent(
            @PathVariable("content") String content,
            @PathVariable("type") int type,
            @PathVariable("pageIndex") int pageIndex);

}
