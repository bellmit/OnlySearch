package application.service.feign.tengxun;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: wtl
 * @License: (C) Copyright 2020. Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020-09-12 16:39
 * @Version: 1.0
 * @Description: 腾讯视频的接口
 */
@FeignClient(name = "TenXunFeign",url = "https://v.qq.com")
public interface TenXunFeign {

    /**
     * 获取腾讯视频电视剧的接口
     * @param offset 偏移量
     * @param pageSize 页大小
     * @param feature feature
     * @param iarea iarea
     * @param pay pay
     * @param sort sort
     * @param year 年份
     * @return String html part
     */
    @GetMapping("/x/bu/pagesheet/list?append=1&channel=tv&offset={offset}&pagesize={pageSize}&feature={feature}&iarea={iarea}&pay={pay}&sort={sort}&year={year}")
    String pageTvList(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String feature,@PathVariable String iarea,@PathVariable String pay,@PathVariable String sort,@PathVariable String year);
}
