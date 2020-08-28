package application.service.feign.kuwo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: wtl
 * @License: (C) Copyright 2020
 * @Contact: 1050100468@qq.com
 * @Date: 2020/8/9 11:40
 * @Version: 1.0
 * @Description:
 */
@FeignClient(name = "SearchKuwoFeign",url = "http://search.kuwo.cn")
public interface SearchKuwoFeign {

    /**
     * 获取专辑的列表
     * @param pageIndex 页码
     * @param pageSize 每页大小
     * @param ablumId ablum id
     * @return String
     */
    @GetMapping("/r.s?pn={pageIndex}&rn={pageSize}&albumid={ablumId}&stype=albuminfo&show_copyright_off=1&alflac=1&pcmp4=1&encoding=utf8&plat=pc&vipver=MUSIC_9.1.1.2_W4&devid=72713798&newver=1&pcjson=1")
    String albums(
            @PathVariable int pageIndex,
            @PathVariable int pageSize,
            @PathVariable String ablumId
    );
}
