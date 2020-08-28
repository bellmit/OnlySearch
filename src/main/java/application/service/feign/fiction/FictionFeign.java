package application.service.feign.fiction;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: 访问外部接口
 * @date 2020-03-21 17:00:39
 */
@FeignClient(name = "FictionFeign", url = "http://book.zongheng.com")
public interface FictionFeign {

    /**
     * 列出分页的结果
     *
     * @param pageIndex 页码
     * @return html String
     */
    @GetMapping(value = "/store/c0/c0/b0/u0/p{pageIndex}/v0/s9/t0/u0/i1/ALL.html", headers = {
            "User-Agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3732.400 QQBrowser/10.5.3819.400"
    })
    String listFictions(@PathVariable("pageIndex") int pageIndex);

    /**
     * 获取章节页面
     *
     * @param id
     * @return html String
     */
    @GetMapping(value = "/showchapter/{id}.html", headers = {
            "User-Agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3732.400 QQBrowser/10.5.3819.400"
    })
    String showChapter(@PathVariable("id") String id);

    /**
     * 获取单个chapter章节的内容
     * @param id
     * @param chapterId
     * @return html String
     */
    @GetMapping(value = "/chapter/{id}/{chapterId}.html",headers = {
            "User-Agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3732.400 QQBrowser/10.5.3819.400"
    })
    String getChapter(
            @PathVariable("id") String id,
            @PathVariable("chapterId") String chapterId);
}
