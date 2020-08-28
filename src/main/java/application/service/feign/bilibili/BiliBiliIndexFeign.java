package application.service.feign.bilibili;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * bilibili的index
 * @author Administrator
 */
@FeignClient(name = "BiliBiliIndexFeign",url = "https://www.bilibili.com")
public interface BiliBiliIndexFeign {
    /**
     * 获取hotTags列表
     * @param url url网址
     * @return String
     */
    @GetMapping("/{url}")
    String getHotTags(@PathVariable("url") String url);

    /**
     * 获取bVid
     * @param bVid
     * @return html
     */
    @GetMapping(value = "/video/{bVid}",
            headers = {
                    "User-Agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3732.400 QQBrowser/10.5.3819.400"
            })
    Response getVideo(@PathVariable String bVid);

}
