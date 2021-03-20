package application.service.feign.metv;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "MetvMovieFegin",url = "https://ljxwtl.cn:8080")
public interface MetvFegin {

    @GetMapping("/metv/listPcWeb?platform=pcweb&channelId=3&pageIndex={pageIndex}&pageSize=80&hudong=1&_support=10000000&kind=a1&area=&sort=c2&abroad=&src=&year=&edition=&chargeInfo=&fitAge=&musicStyle=")
    String getMetvMovieList(
            @PathVariable int pageIndex
    );

    @GetMapping("/metv/listPcWeb?platform=pcweb&channelId=2&pageIndex={pageIndex}&pageSize=80&hudong=1&_support=10000000&kind=a1&area=&sort=c2&abroad=&src=&year=&edition=&chargeInfo=&fitAge=&musicStyle=")
    String getMetvTvList(
            @PathVariable int pageIndex
    );

    @GetMapping("/metv/getDocumentaryList/{vid}/{cid}")
    String getPageToList(
         @PathVariable String vid,
         @PathVariable String cid
    );

}
