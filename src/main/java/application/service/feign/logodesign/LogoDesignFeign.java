package application.service.feign.logodesign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "LoginDesignFeign",url = "http://www.uugai.com")
public interface LogoDesignFeign {

    @GetMapping("/logoa/wenzi.php")
    public String getZitiList();

    @GetMapping("/")
    public String index();
}
