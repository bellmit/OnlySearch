package application.service.feign.index;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "IpFegin",url = "http://ip.t086.com")
public interface IpFegin {

    /**
     * ip转地区
     * @param ip
     * @return
     */
    @GetMapping("/?ip={ip}")
    Response ip2Address(@PathVariable("ip") String ip);
}
