package application.service.feign.index;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Hao123Fegin", url = "https://www.hao123.com")
public interface Hao123Fegin {

    /**
     * 推荐
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/feedData/data?type=rec&pn={pageNumber}&rn={pageSize}&app_from=indexnew_feed")
    String recommend(
            @PathVariable("pageNumber") int pageNumber,
            @PathVariable("pageSize") int pageSize);
}
