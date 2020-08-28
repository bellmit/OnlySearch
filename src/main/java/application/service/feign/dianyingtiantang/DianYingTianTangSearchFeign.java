package application.service.feign.dianyingtiantang;

import feign.RequestLine;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;

import java.net.URI;

/**
 * @author wtl
 * @program: springboot
 * @description: 电影天堂的搜索Feign接口
 * @date 2020-03-02 16:55:59
 */
@FeignClient("DianYingTianTangSearchFeign")
public interface DianYingTianTangSearchFeign {
    /**
     * 获取下载页面的内容
     * @param uri
     * @return
     */
    @RequestLine("GET")
    Response getDownloadPageContent(URI uri);
}
