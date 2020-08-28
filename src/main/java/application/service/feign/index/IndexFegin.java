package application.service.feign.index;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "IndexFegin",url = "https://www.hao123.com")
public interface IndexFegin {
    @GetMapping("/")
    String index();
}
