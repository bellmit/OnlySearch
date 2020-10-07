package application.service.feign.xiguavideo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: wtl
 * @License: (C) Copyright 2020, wtl Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020-10-07 14:25
 * @Version: 1.0
 * @Description:
 */
@FeignClient(name = "XiGuaVideoFeign",url = "https://www.ixigua.com")
public interface XiGuaVideoFeign {

    /**
     * 获取详细的电视或电影的剧集列表和推荐列表
     * @param albumId album id
     * @return String json
     */
    @GetMapping(value = "/api/albumv2/details?albumId={albumId}",headers = {
            "referer=https://www.ixigua.com/6532733952283640333?logTag=FnZEHs9YIxCXkiJ8Z7Kzr"
    })
    String detailPagesTvOrMovie(@PathVariable String albumId);
}
