package application.service.feign.cloudMusic;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: ApiImjadCnFeign
 * @date 2020-04-04 14:19:23
 */
@FeignClient(name = "ApiImjadCnFeign2",url = "https://api.imjad.cn")
public interface ApiImjadCnFeign {

    /**
     * 获取歌曲详情
     * @param songId
     * @return
     */
    @GetMapping("/cloudmusic/?type=song&id={songId}")
    String getSong(
            @PathVariable("songId") String songId
            );
}
