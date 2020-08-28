package application.service.feign.multimusic;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 虾米音乐
 */
@FeignClient(value = "MusicXiamiFegin",url = "https://www.xiami.com")
public interface MusicXiamiFegin {

}
