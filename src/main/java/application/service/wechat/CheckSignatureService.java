package application.service.wechat;

import application.model.wechat.InMsgEntity;
import application.service.wechat.message.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author wtl
 */
@Service
@Slf4j
public class CheckSignatureService {

    @Resource
    private TextMessage textMessage;

    public boolean checkToken(String signature,
                              String timestamp,
                              String nonce,
                              String echostr) {
        String token = "ljxwtl";
        String strs[] = {token, timestamp, nonce};

        //对字符串数组进行字典排序
        Arrays.sort(strs);

        //拼接排序后的字符串数组
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            stringBuilder.append(strs[i]);
        }

        //对拼接的字符串进行SHA-1加密
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] digestBytes = messageDigest.digest(stringBuilder.toString().getBytes());

        //将字节数组进行转换成十六进制转换
        char digist[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        String result = "";
        for (int i = 0; i < digestBytes.length; i++) {
            char tmpChars[] = new char[2];
            tmpChars[0] = digist[(digestBytes[i] >>> 4) & 0X0F];
            tmpChars[1] = digist[digestBytes[i] & 0X0F];
            result += new String(tmpChars);
        }
        log.info("#########################################");
        log.info("传递的signature：" + signature);
        log.info("加密后的signature：" + result);
        log.info("#########################################");

        return result.equals(signature.toUpperCase());
    }

    /**
     * 处理发送过来的post消息
     * @param inMsgEntity
     * @return String
     * @throws IOException Exception
     */
    public String dealMultipleMessage(InMsgEntity inMsgEntity) throws Exception {
        switch (inMsgEntity.getMsgType()){
            case "text":
                return textMessage.dealMessage(inMsgEntity);
            default:
                break;
        }

        return null;
    }
}
