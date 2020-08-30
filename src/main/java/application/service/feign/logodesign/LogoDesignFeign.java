package application.service.feign.logodesign;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author wtl
 */
@FeignClient(value = "LoginDesignFeign",url = "http://www.uugai.com/")
public interface LogoDesignFeign {
    /**
     * 首页
     * @return String
     */
    @GetMapping("/")
    String index();

    /**
     * 获取分页的文字类型
     * @param pageIndex 页码
     * @return json String
     */
    @GetMapping("/logoa/wenzi.php?page={pageIndex}")
    String getWenziListByPage(@PathVariable int pageIndex);

    /**
     * 获取图片根据logo类型
     * @param logodw logo类型
     * @param pageIndex 页码
     * @return
     */
    @GetMapping("/logoa/{logodw}.php?page={pageIndex}")
    String getImagesByLogoStyle(@RequestHeader("Cookie") String cookie,
                                @PathVariable String logodw, @PathVariable int pageIndex);

    /**
     * 获取log_img_sc
     * @param cookie Cookie
     * @return byte[]
     */
    @GetMapping("/logoa/logo_img_sc.php")
    byte[] getLogImageSrc(@RequestHeader("Cookie") String cookie);

}
