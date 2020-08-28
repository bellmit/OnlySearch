package application.service.feign.multimusic;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "MusicXimalayaFeign", url = "https://www.ximalaya.com")
public interface MusicXimalayaFeign {

    @GetMapping("/revision/category/queryCategoryPageAlbums?category=youshengshu&page={pageIndex}&perPage={limit}")
    String getSongLists(@PathVariable("pageIndex") long pageIndex, @PathVariable("limit") long limit);

    @GetMapping("/revision/play/v1/show?id={id}&num={pageIndex}&sort=-1&size=30&ptype=0")
    String getSongListInfo(@PathVariable("id") String id,
                           @PathVariable("pageIndex") int pageIndex);
}
