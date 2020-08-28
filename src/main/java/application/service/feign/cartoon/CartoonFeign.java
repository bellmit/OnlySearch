package application.service.feign.cartoon;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "CartoonFeignIndex" , url = "https://m.gufengmh8.com")
public interface CartoonFeign {

    @GetMapping(value = "/search/")
    Response index();

    /**
     * 搜索功能
     * @param keyword 关键词
     * @param pageIndex 页码
     * @return
     */
    @GetMapping(value = "/search/?keywords={keyword}&page={pageIndex}")
    String search(@PathVariable String keyword,@PathVariable int pageIndex);

    /**
     * 只请求头信息
     * @param prefixUrl
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "{prefixUrl}{pageIndex}/",method = RequestMethod.HEAD)
    Response list(@PathVariable("prefixUrl") String prefixUrl, @PathVariable("pageIndex") int pageIndex);

    @GetMapping("{prefixUrl}{pageIndex}/")
    Response list1(@PathVariable("prefixUrl") String prefixUrl, @PathVariable("pageIndex") int pageIndex);

    @GetMapping("{prefixUrl}")
    Response list2(@PathVariable("prefixUrl") String prefixUrl);

    /**
     * 获取内容列表
     * @param prefixUrl
     * @param pageIndex
     * @return
     */
    @GetMapping("{prefixUrl}{pageIndex}/")
    String getListHtml(@PathVariable("prefixUrl") String prefixUrl, @PathVariable("pageIndex") int pageIndex);

}
