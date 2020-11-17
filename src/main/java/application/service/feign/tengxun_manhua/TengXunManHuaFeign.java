package application.service.feign.tengxun_manhua;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    @GetMapping(value = "/ComicView/index/id/{id}/cid/{cid}",headers = {
            "user-agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3776.400 QQBrowser/10.6.4212.400;"
    })
    String getTengXunManHuaChapterHtml(@PathVariable String id,@PathVariable String cid);

    /**
     * 分页获取全部漫画的列表
     * @param pageIndex 页码
     * @return String html
     */
    @GetMapping(value = "/Comic/all/search/hot/vip/1/page/{pageIndex}",headers = {
            "user-agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3776.400 QQBrowser/10.6.4212.400;"
    })
    String getTengXunManHuaAllList(@PathVariable int pageIndex);


    /**
     * 分页获取全部漫画的列表
     * @param classify 分类
     *                 恋爱：105
     *                 玄幻：101
     *                 异能：103
     *                 恐怖：110
     *                 剧情：106
     *                 科幻：108
     *                 悬疑：112
     *                 奇幻：102
     *                 冒险：104
     *                 犯罪：111
     *                 动作：109
     *                 日常：113
     *                 竞技：114
     *                 武侠：115
     *                 历史：116
     * @param pageIndex 页码
     * @return String html
     */
    @GetMapping(value = "/Comic/all/theme/{classify}/search/hot/vip/1/page/{pageIndex}",headers = {
            "user-agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3776.400 QQBrowser/10.6.4212.400;"
    })
    String getTengXunManHuaClassifyList(@PathVariable String classify,@PathVariable int pageIndex);


    /**
     * 获取漫画的详情根据漫画id
     * @param id id唯一标识
     * @return String html
     */
    @GetMapping(value = "/Comic/comicInfo/id/{id}",headers = {
            "user-agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3776.400 QQBrowser/10.6.4212.400;"
    })
    String getTengXunManHuaInfoById(@PathVariable String id);
}
