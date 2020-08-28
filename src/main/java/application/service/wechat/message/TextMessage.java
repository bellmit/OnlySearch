package application.service.wechat.message;

import application.filter.SysContext;
import application.model.wechat.InMsgEntity;
import application.service.feign.wechat.TuLingFeign;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: wtl
 * @Date: 2020/7/9
 * @Time: 6:53
 * @Description:
 */
@Component
public class TextMessage implements Message{

    @Resource
    private TuLingFeign tuLingFeign;

    @Resource
    private NewsMessage newsMessage;

    @Override
    public String dealMessage(InMsgEntity inMsgEntity) {
        String returnMessage = tuLingFeign.getReturnMessage(SysContext.TU_LING_REQUEST_CONTENT.replaceAll("MESSAGE", inMsgEntity.getContent())
                .replaceAll("API_KEY", SysContext.TU_LING_API_TOKEN)
                .replaceAll("USER_ID", SysContext.TU_LING_USER_ID));
        String responseText = JSONObject.fromObject(returnMessage).getJSONArray("results").getJSONObject(0).getJSONObject("values").getString("text");

        switch (inMsgEntity.getContent()) {
            case SysContext.TYPE_TV:
            case SysContext.TYPE_MOVIE:
            case SysContext.TYPE_PICTURE:
            case SysContext.TYPE_APP:
            case SysContext.TYPE_MUSIC:
            case SysContext.TYPE_CARTOON:
            case SysContext.TYPE_FICTION:
                return newsMessage.dealMessage(inMsgEntity);
            default:
                return SysContext.WE_CHAT_TEXT_MESSAGE_TEMPLATE
                        .replaceAll("TO_USER", inMsgEntity.getFromUserName())
                        .replaceAll("FROM_USER", inMsgEntity.getToUserName())
                        .replaceAll("CREATE_TIME", String.valueOf(System.currentTimeMillis()))
                        .replaceAll("CONTENT", responseText);
        }
    }
}
