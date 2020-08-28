package application.service.feign.index;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "TopBaiduBuzzFegin",url = "http://top.baidu.com")
public interface TopBaiduBuzzFegin {

    @GetMapping("/buzz?b=1&fr=20811")
    Response topBaiduBuzz();
}
