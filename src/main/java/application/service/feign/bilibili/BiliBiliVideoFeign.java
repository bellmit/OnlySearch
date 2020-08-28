package application.service.feign.bilibili;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Administrator
 */
@FeignClient(name = "BiliBiliVideoFeign",url = "https://api.bilibili.com")
public interface BiliBiliVideoFeign {

    /**
     * newList的查询
     * @param rid rid
     * @param pageIndex 页码
     * @return String 返回的结果
     */
    @GetMapping("/x/web-interface/newlist?rid={rid}&type=0&pn={pageIndex}&ps=20")
    String getNewList(@PathVariable("rid") String rid, @PathVariable("pageIndex") int pageIndex);

    /**
     * 获取实时动态列表
     * @param rid rid
     * @param pageIndex 页码
     * @return String 返回的结果
     */
    @GetMapping("/x/web-interface/dynamic/region?rid={rid}&type=0&pn={pageIndex}&ps=5")
    String getDynamic(@PathVariable("rid") String rid, @PathVariable("pageIndex") int pageIndex);

    /**
     * 获取排名
     * @param rid rid
     * @return String 返回的结果
     */
    @GetMapping("/x/web-interface/ranking/region?rid={rid}&day=7&original=0")
    String getRanking(@PathVariable("rid") String rid);

    /**
     * 根据tag_id获取NewList
     * @param rid rid
     * @param pageIndex 页码
     * @param tagId tag_id
     * @return String 返回的结果
     */
    @GetMapping("/x/tag/ranking/archives?rid={rid}&type=0&pn={pageIndex}&ps=20&tag_id={tagId}")
    String getNewListByTagId(@PathVariable("rid") String rid, @PathVariable("pageIndex") int pageIndex, @PathVariable("tagId") int tagId);

    /**
     * 根据tag_id获取实时动态列表
     * @param rid rid
     * @param pageIndex 页码
     * @param tagId tag_id
     * @return String 返回的数据
     */
    @GetMapping("/x/web-interface/dynamic/tag?rid={rid}&type=0&pn={pageIndex}&ps=5&tag_id={tagId}")
    String getDynamicByTagId(@PathVariable("rid") String rid, @PathVariable("pageIndex") int pageIndex, @PathVariable("tagId") int tagId);

    /**
     * 根据tag_id获取排名
     * @param rid rid
     * @param tagId tag_id
     * @return String 返回的结果
     */
    @GetMapping("/x/web-interface/ranking/tag?rid={rid}&day=7&original=0&tag_id={tagId}")
    String getRankingByTagId(@PathVariable("rid") String rid, @PathVariable("tagId") int tagId);

    /**
     * 获取该aid的video的所有的cid信息
     * @param aid
     * @return
     */
    @GetMapping("/x/player/pagelist?aid={aid}&jsonp=jsonp")
    String getBiliBiliVideoPageCids(@PathVariable("aid") String aid);

    /**
     * 获取哔哩哔哩的video的详细信息
     * @param aid
     * @param cid
     * @return
     */
    @GetMapping("/x/player/playurl?avid={aid}&cid={cid}&otype=json")
    String getBiliBiliVideoInfo(@PathVariable("aid") String aid, @PathVariable("cid") String cid);

    /**
     * 获取该aid的video的所有的page
     * @param bVid
     * @return String
     */
    @GetMapping("/x/player/pagelist?bvid={bVid}&jsonp=jsonp")
    String getBiliBiliVideoPageBVids(@PathVariable("bVid") String bVid);

    /**
     * 搜索功能
     * @param keyword 关键词
     * @param order 排序
     * @param duration 时间段
     * @param tids tids
     * @return html
     */
    @GetMapping(value = "/all?keyword={keyword}&order={order}&duration={duration}&tids_1={tids}&page={pageIndex}",
            headers = {
                    "Cookie=main_confirmation=76cdbVS/JXKJLsrlCPEz9l1t/eE2/StcZpi1VYRVBNk=",
                    "Host=search.bilibili.com",
                    "User-Agent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3732.400 QQBrowser/10.5.3819.400"
            })
    String getAid(@PathVariable("keyword") String keyword,
                  @PathVariable("order") String order,
                  @PathVariable("duration") String duration,
                  @PathVariable("tids") String tids,
                  @PathVariable("pageIndex") int pageIndex);


    /**
     * 获取哔哩哔哩的video的详细信息
     * @param cid
     * @param bVid
     * @return
     */
    @GetMapping("/x/player/playurl?cid={cid}&bvid={bVid}&qn=116&type=mp4")
    String getBiliBiliVideo(@PathVariable String cid, @PathVariable String bVid);
}
