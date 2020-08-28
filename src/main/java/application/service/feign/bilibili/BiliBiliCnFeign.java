package application.service.feign.bilibili;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wtl
 * @program: springboot
 * @description: Bilibili API
 * @date 2020-03-03 19:55:38
 */
@FeignClient(name = "BiliBiliCnFeign",url = "http://api.bilibili.cn/")
public interface BiliBiliCnFeign {

    /**
     * 获取视频信息
     * @param appKey appkey
     * @param aid aid
     * @param pageIndex 页码
     * @return json String
     */
    @GetMapping("/view?appkey={appKey}&id={aid}&page={pageIndex}")
    String getVideoInfo(@PathVariable("appKey") String appKey,
                        @PathVariable("aid") String aid,
                        @PathVariable("pageIndex") int pageIndex);
}
