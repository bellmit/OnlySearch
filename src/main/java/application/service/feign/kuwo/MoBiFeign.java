package application.service.feign.kuwo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: wtl
 * @License: (C) Copyright 2020
 * @Contact: 1050100468@qq.com
 * @Date: 2020/8/9 16:44
 * @Version: 1.0
 * @Description:
 */
@FeignClient(name = "MoBiFeign",url = "http://mobi.kuwo.cn")
public interface MoBiFeign {
    /**
     * radio list
     * @return json String
     */
    @GetMapping("/mobi.s?type=radiolist&rformat=json&uid=72713798&loginUid=0&prod=MUSIC_9.1.1.2_W4 ")
    String radioList();
}
