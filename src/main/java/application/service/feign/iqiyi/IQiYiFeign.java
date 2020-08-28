package application.service.feign.iqiyi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wtl
 * @program: springboot
 * @description:
 * @date 2020-03-13 16:45:39
 */
@FeignClient(name = "IQiYiFeign", url = "https://pcw-api.iqiyi.com")
public interface IQiYiFeign {

    /**
     * 电视剧的api
     *
     * @param channelId              频道
     * @param marketReleaseDateLevel 日期
     * @param mode                   模式
     * @param pageNumber             页码
     * @param pageSize               页面大小
     * @param area                   地区
     * @param type                   类型
     * @param other                  其他选项
     * @return json String
     */
    @GetMapping("/search/video/videolists?access_play_control_platform=14&channel_id={channelId}&data_type={dataType}&from=pcw_list&is_album_finished={finished}&is_purchase=&key=&market_release_date_level={marketReleaseDateLevel}&mode={mode}&pageNum={pageNumber}&pageSize={pageSize}&site=iqiyi&source_type=&three_category_id={area}{type}{version}{other}{other2}{other3}&without_qipu=1")
    String searchTv(
            @PathVariable("channelId") String channelId,
            @PathVariable("dataType") String dataType,
            @PathVariable("finished") String finished,
            @PathVariable("marketReleaseDateLevel") String marketReleaseDateLevel,
            @PathVariable("mode") String mode,
            @PathVariable("pageNumber") String pageNumber,
            @PathVariable("pageSize") String pageSize,
            @PathVariable("area") String area,
            @PathVariable("type") String type,
            @PathVariable("version") String version,
            @PathVariable("other") String other,
            @PathVariable("other2") String other2,
            @PathVariable("other3") String other3);

    /**
     * 获取aid
     * @param tVid
     * @return json String
     */
    @GetMapping("/video/video/playervideoinfo?tvid={tVid}&locale=cn_s")
    String getAid(@PathVariable("tVid") String tVid);
}
