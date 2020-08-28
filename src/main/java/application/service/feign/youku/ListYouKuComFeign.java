package application.service.feign.youku;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: 优酷feign
 * @date 2020-04-06 07:42:38
 */
@FeignClient(name = "ListYouKuComFeign",url = "https://list.youku.com")
public interface ListYouKuComFeign {

    /**
     * 获取类别的列表
     * @param classify 分类
     * 97 剧集
     * 96 电影
     * 85 综艺
     * 100 动漫
     * 177 少儿
     * 95 音乐
     * 87 教育
     * 84 纪录片
     * 98 体育
     * 178 文化
     * 86 娱乐
     * 99 游戏
     * 91 资讯
     * 94 搞笑
     * 103 生活
     * 104 汽车
     * 105 科技
     * 89 时尚
     * 90 亲子
     * 88 旅游
     * 171 微电影
     * 172 网剧
     * 174 拍客
     * 175 创意视频
     * @param type 类型
     * @param time 时间
     * @param area 地区
     * @param videoType 视频类型
     * @param pageIndex 页码
     * @param stamp 类型
     * @param language 语言
     * @return json String
     */
    @GetMapping(value = "/category/page?c={classify}&g={type}&r={time}&a={area}&type={videoType}&p={pageIndex}&mt={stamp}&lg={language}",headers = {
            "User-Agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3732.400 QQBrowser/10.5.3819.400"
    })
    String categoryList(
            @PathVariable("classify") int classify,
            @PathVariable("type") String type,
            @PathVariable("time") String time,
            @PathVariable("area") String area,
            @PathVariable("videoType") String videoType,
            @PathVariable("pageIndex") int pageIndex,
            @PathVariable("stamp") String stamp,
            @PathVariable("language") String language
    );
}
