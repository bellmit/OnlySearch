package application.service.feign.wechat;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wtl
 * @program: springboot
 * @description: MultipleServerFeign接口类
 * @date 2020-03-02 16:25:46
 */
@FeignClient(name = "MultipleServerFeign",url = "https://api.weixin.qq.com")
public interface WeChatServerFeign {

    /**
     * 获取access_token
     * @param appId app ID
     * @param appSecret aap Secret
     * @return String
     */
    @GetMapping("/cgi-bin/token?grant_type=client_credential&appid={appId}&secret={appSecret}")
    String getAccessToken(@PathVariable("appId") String appId,
                          @PathVariable("appSecret") String appSecret);


    /**
     * 获取微信服务器IP地址
     * @param accessToken access_token
     * @return String
     */
    @GetMapping("/cgi-bin/getcallbackip?access_token={accessToken}")
    String getWeChatServerIpAddress(@PathVariable("accessToken") String accessToken);

}
