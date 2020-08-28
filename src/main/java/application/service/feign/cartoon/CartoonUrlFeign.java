package application.service.feign.cartoon;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "CartoonUrlFeign",url = "https://res.gufengmh8.com")
public interface CartoonUrlFeign {

    @RequestMapping(value = "{url}",method = RequestMethod.HEAD)
    Response getUrl(@PathVariable("url") String url);

}
