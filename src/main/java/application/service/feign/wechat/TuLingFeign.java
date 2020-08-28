package application.service.feign.wechat;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author: wtl
 * @Date: 2020/7/9
 * @Time: 7:12
 * @Description:
 */
@FeignClient(name = "TuLingFeign",url = "http://openapi.tuling123.com")
public interface TuLingFeign {

    /**
     * 获取返回的message
     * @param content 内容
     * @return String message
     */
    @PostMapping("/openapi/api/v2")
    String getReturnMessage(@RequestBody String content);
}
