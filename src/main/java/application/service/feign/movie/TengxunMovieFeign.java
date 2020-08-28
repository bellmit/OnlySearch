package application.service.feign.movie;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "TengxunMovieFeign",url = "https://v.qq.com")
public interface TengxunMovieFeign {

    @GetMapping("/x/bu/pagesheet/list?append=1&channel=movie&itype=100062&listpage=2&offset={pageIndex}&pagesize={pageSize}")
    String searchMovie(@PathVariable("pageIndex") int pageIndex,
                       @PathVariable("pageSize") int pageSize);
}
