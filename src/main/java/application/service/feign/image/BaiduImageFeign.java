package application.service.feign.image;

import feign.Headers;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "BaiduImageFeign", url = "https://image.baidu.com")
public interface BaiduImageFeign {

    @GetMapping("/search/acjson?tn=resultjson_com&logid=4142370750610916672&ipn=rj&ct=201326592&fp=result&queryWord={keyword}&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&word={keyword}&pn={offset}&rn={pageSize}&gsm=12c")
    Response searchByKeyword(@PathVariable String keyword,
                             @PathVariable int offset,
                             @PathVariable int pageSize);

    @GetMapping(value = "/search/acjson?tn=resultjson_com&logid=4142370750610916672&ipn=rj&ct=201326592&fp=result&queryWord={keyword}&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&word={keyword}&pn={offset}&rn={pageSize}&gsm=12c",
            headers={"User-Agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3861.400 QQBrowser/10.7.4313.400"})
    String searchByKeywordWithCookies(@PathVariable String keyword,
                                      @PathVariable int offset,
                                      @PathVariable int pageSize,
                                      @RequestHeader("Cookie") String cookies);
}
