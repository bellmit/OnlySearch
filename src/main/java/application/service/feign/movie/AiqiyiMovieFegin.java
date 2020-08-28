package application.service.feign.movie;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "AiqiyiMovieFegin",url = "https://pcw-api.iqiyi.com")
public interface AiqiyiMovieFegin {

    /**
     * 搜索movie方法
     * @param pageIndex pageIndex
     * @param pageSize 页面大小
     * @return String
     */
    @GetMapping("/search/video/videolists?access_play_control_platform=14&channel_id=1&data_type=1&from=pcw_list&is_album_finished=&is_purchase=&key=&market_release_date_level=&mode=24&pageNum={pageIndex}&pageSize={pageSize}&site=iqiyi&source_type=&three_category_id=&without_qipu=1")
    String searchMovie(@PathVariable("pageIndex") int pageIndex,
                       @PathVariable("pageSize") int pageSize);

    /**
     * 分析爱奇艺数据
     * @param pageIndex 页码
     * @return String
     */
    @GetMapping("/search/video/videolists?access_play_control_platform=14&channel_id=1&data_type=1&from=pcw_list&is_album_finished=&is_purchase=0&key=&market_release_date_level=&mode=24&pageNum={pageIndex}&pageSize=48&site=iqiyi&source_type=&three_category_id=&without_qipu=1")
    String analyseBigData(@PathVariable("pageIndex") int pageIndex);

}
