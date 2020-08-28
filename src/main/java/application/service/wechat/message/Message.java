package application.service.wechat.message;

import application.model.wechat.InMsgEntity;

/**
 * @author: wtl
 * @Date: 2020/7/9
 * @Time: 6:51
 * @Description: Message 接口
 */
public interface Message {
    /**
     * 处理message
     * @param inMsgEntity message
     * @return Xml String
     */
    String dealMessage(InMsgEntity inMsgEntity);
}
