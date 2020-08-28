package application.service.feign.youku;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


/**
 * @author wtl
 * @program: OnlySearch
 * @description: AcsYouKuComFeign
 * @date 2020-05-16 17:40:56
 */
@FeignClient(value = "AcsYouKuComFeign",url = "https://acs.youku.com")
public interface AcsYouKuComFeign {

    /**
     * 获取_m_h5_tk和_m_h5_tk_enc
     * @return Response
     */
    @GetMapping("/h5/mtop.youku.subscribe.service.subscribe.favourite.batchisfav/3.0/?jsv=2.5.8&appKey=24679788&t=1589617173164&sign=60685aeb7da80927675a5e803fb6e9c5&api=mtop.youku.subscribe.service.subscribe.favourite.batchisfav&type=jsonp&v=3.0&ecode=1&dataType=jsonp&jsonpIncPrefix=pc_playpage_paction&callback=mtopjsonppc_playpage_paction1&data={\"itemType\":\"\\\"SHOW\\\"\",\"itemIds\":\"3aefbfbd0519d5a911ef\",\"src\":\"PC_PLAY\"}")
    Response getTokens();

    /**
     * 获取videoList列表
     * @param timeMillis 时间戳
     * @param sign sign
     * @param data 数据
     * @param cookies cookies
     * @return String
     */
    @GetMapping("/h5/mtop.youku.columbus.gateway.new.execute/1.0/?jsv=2.5.8&appKey=24679788&t={timeMillis}&sign={sign}&api=mtop.youku.columbus.gateway.new.execute&v=1.0&ecode=1&data={data}")
    String getVideoList(
            @PathVariable("timeMillis") long timeMillis,
            @PathVariable("sign") String sign,
            @PathVariable("data") String data,
            @RequestHeader("Cookie") String cookies
    );

}
