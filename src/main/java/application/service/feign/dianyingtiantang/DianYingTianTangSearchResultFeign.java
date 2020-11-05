package application.service.feign.dianyingtiantang;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wtl
 * @program: springboot
 * @description: 电影天堂搜索结果的接口
 * @date 2020-03-02 17:42:46
 */
@FeignClient(name = "DianYingTianTangSearchResultFeign",url = "http://s.ygdy8.com")
public interface DianYingTianTangSearchResultFeign {

    /**
     * 获取搜索页面的结果
     * @param keyword 关键词
     * @param typeId typeId
     * @param pageIndex 页码
     * @return String
     */
    @GetMapping("/plus/so1.php?keyword={keyword}&searchtype=titlekeyword&channeltype=0&orderby=&kwtype=0&pagesize=10&typeid={typeId}&PageNo={pageIndex}")
    Response getSearchPagingResult(@PathVariable("keyword") String keyword,
                                   @PathVariable("typeId") int typeId,
                                   @PathVariable("pageIndex") int pageIndex);
}
