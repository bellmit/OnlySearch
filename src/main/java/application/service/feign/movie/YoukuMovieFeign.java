package application.service.feign.movie;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "YoukuMovieFeign",url = "https://list.youku.com")
public interface YoukuMovieFeign {

    @GetMapping("/category/page?c=96&type=show&p={pageIndex}")
    String searchMovie(@PathVariable("pageIndex") int pageIndex);
}
