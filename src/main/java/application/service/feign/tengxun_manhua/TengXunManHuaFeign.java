package application.service.feign.tengxun_manhua;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: wtl
 * @License: (C) Copyright 2020, wtl Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020/11/15 10:02
 * @Version: 1.0
 * @Description:
 */
@FeignClient(name = "TengXunManHuaFeign",url = "https://ac.qq.com")
public interface TengXunManHuaFeign {

    /**
     * 获取腾讯漫画的章节的html string
     * @return html string
     */
    @GetMapping(value = "/ComicView/index/id/541812/cid/7",headers = {
            "user-agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3776.400 QQBrowser/10.6.4212.400;"
    })
    String getTengXunManHuaChapterHtml();
}
