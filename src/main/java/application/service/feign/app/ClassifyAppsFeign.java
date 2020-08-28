package application.service.feign.app;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wtl
 * @program: springboot
 * @description: ClassifyAppsFeign接口
 * @date 2020-03-02 16:16:32
 */
@FeignClient(name = "ClassifyAppsFeign",url = "https://sj.qq.com")
public interface ClassifyAppsFeign {

    /**
     * 获取分类app
     * @param categoryId categoryId
     * @param pageSize 页面大小
     * @param pageContext 页面内容
     * @return String
     */
    @GetMapping("/myapp/cate/appList.htm?orgame=1&categoryId={categoryId}&pageSize={pageSize}&pageContext={pageContext}")
    String getClassifyApps(@PathVariable("categoryId") int categoryId,
                           @PathVariable("pageSize") int pageSize,
                           @PathVariable("pageContext") int pageContext);

}
