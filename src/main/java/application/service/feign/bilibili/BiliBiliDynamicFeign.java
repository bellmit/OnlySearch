package application.service.feign.bilibili;

import feign.RequestLine;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;

import java.net.URI;

/**
 * @author Administrator
 */
@FeignClient(name = "BiliBiliDynamicFeign")
public interface BiliBiliDynamicFeign {
    @RequestLine("GET")
    Response getVideoStream(URI uri);
}
