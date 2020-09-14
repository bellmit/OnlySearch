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
@FeignClient(name = "TenXunFeign", url = "https://v.qq.com")
public interface TenXunFeign {

    /**
     * 获取腾讯视频电视剧的接口
     *
     * @param channel        频道
     * @param offset         偏移量
     * @param pageSize       页大小
     * @param feature        feature
     * @param iarea          iarea
     * @param pay            pay
     * @param sort           sort
     * @param year           年份
     * @param charge         资费
     * @param itype          类型
     * @param characteristic 特色
     * @return String html part
     */
    @GetMapping("/x/bu/pagesheet/list?append=1&channel={channel}&offset={offset}&pagesize={pageSize}&feature={feature}&iarea={iarea}&pay={pay}&sort={sort}&year={year}&charge={charge}&itype={itype}&characteristic={characteristic}&ipay={ipay}&iyear={iyear}&source={source}&exclusive={exclusive}&plot_aspect={plot_aspect}&language={language}&anime_status={anime_status}&itrailer={itrailer}&cuisine_style={cuisine_style}&food_itype={food_itype}&iaspect={iaspect}&icolumn={icolumn}&icelebrity={icelebrity}&category={category}&time={time}&class_type={class_type}")
    String pageList(@PathVariable String channel, @PathVariable int offset, @PathVariable int pageSize, @PathVariable String feature, @PathVariable String iarea, @PathVariable String pay, @PathVariable String sort, @PathVariable String year, @PathVariable String charge, @PathVariable String itype, @PathVariable String characteristic, @PathVariable String ipay, @PathVariable String iyear, @PathVariable String source, @PathVariable String exclusive,
                    @PathVariable String plot_aspect,@PathVariable String language,@PathVariable String anime_status,@PathVariable String itrailer,@PathVariable String cuisine_style,@PathVariable String food_itype,@PathVariable String iaspect,@PathVariable String icolumn,@PathVariable String icelebrity,@PathVariable String category,@PathVariable String time,@PathVariable String class_type);

}
