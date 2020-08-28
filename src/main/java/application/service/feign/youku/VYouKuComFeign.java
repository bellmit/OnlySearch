package application.service.feign.youku;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: v.youku.com
 * @date 2020-04-06 10:27:09
 */
@FeignClient(name = "VYouKuComFeign",url = "https://v.youku.com")
public interface VYouKuComFeign {

    /**
     * 获取video页面
     * @param videoId videoId
     * @return html String
     */
    @GetMapping(value = "/v_show/id_{videoId}.html",headers = {
            "user-agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3756.400 QQBrowser/10.5.4039.400"
    })
    Response videoListPage(@PathVariable("videoId") String videoId);

}
