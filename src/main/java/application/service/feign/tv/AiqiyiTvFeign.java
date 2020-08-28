package application.service.feign.tv;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "AiqiyiTvFeign", url = "https://pcw-api.iqiyi.com")
public interface AiqiyiTvFeign {

    @GetMapping("/search/video/videolists?access_play_control_platform=14&channel_id=2&data_type=1&from=pcw_list&is_album_finished=&is_purchase=&key=&market_release_date_level=&mode=24&pageNum={pageIndex}&pageSize={pageSize}&site=iqiyi&source_type=&three_category_id=&without_qipu=1")
    String searchTv(@PathVariable("pageIndex") int pageIndex,
                    @PathVariable("pageSize") int pageSize);

    @GetMapping("/albums/album/avlistinfo?aid={aid}&page={pageIndex}&size={size}")
    String showTv(@PathVariable("aid") String aid, @PathVariable("pageIndex") int pageIndex, @PathVariable("size") int size);
}
