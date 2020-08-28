package application.controller.wechat.servlet;

import application.filter.SysContext;
import application.model.wechat.InMsgEntity;
import application.service.wechat.CheckSignatureService;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author: wtl
 * @Date: 2020/7/6
 * @Time: 6:15
 * @Description:
 */
@WebServlet(name = "CheckSignatureServlet",urlPatterns = "/wx")
public class CheckSignatureServlet extends HttpServlet {

    @Resource
    private CheckSignatureService checkSignatureService;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String signature = httpServletRequest.getParameter("signature");
        String timestamp = httpServletRequest.getParameter("timestamp");
        String nonce = httpServletRequest.getParameter("nonce");
        String echostr = httpServletRequest.getParameter("echostr");

        boolean bool = checkSignatureService.checkToken(signature, timestamp, nonce, echostr);

        if (bool){
            BufferedOutputStream bufferedOutputStream = null;
            try {
                bufferedOutputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
                bufferedOutputStream.write(echostr.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (bufferedOutputStream != null){
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        BufferedReader reader = httpServletRequest.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while((line = reader.readLine())!=null){
            stringBuilder.append(line);
        }
        String body = stringBuilder.toString();

        Document document = Jsoup.parse(body);
        String toUserName = document.getElementsByTag("tousername").text();
        String fromUserName = document.getElementsByTag("fromusername").text();
        String createTime = document.getElementsByTag("createtime").text();
        String msgType = document.getElementsByTag("msgtype").text();
        String content = document.getElementsByTag("content").text();
        String msgId = document.getElementsByTag("msgid").text();
        String picUrl = document.getElementsByTag("picurl").text();
        String mediaId = document.getElementsByTag("mediaid").text();
        String recognition = document.getElementsByTag("recognition").text();
        String format = document.getElementsByTag("format").text();
        //处理为""的Long的转换
        if (SysContext.BLANK_STRING.equalsIgnoreCase(mediaId)){
            mediaId = SysContext.DEFAULT_NUMBER;
        }

        InMsgEntity inMsgEntity = InMsgEntity.builder()
                .ToUserName(toUserName)
                .FromUserName(fromUserName)
                .CreateTime(Long.parseLong(createTime))
                .MsgType(msgType)
                .Content(content)
                .MsgId(Long.parseLong(msgId))
                .PicUrl(picUrl)
                .MediaId(Long.parseLong(mediaId))
                .Recognition(recognition)
                .Format(format)
                .build();

        String responseText = checkSignatureService.dealMultipleMessage(inMsgEntity);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
        bufferedOutputStream.write(responseText.getBytes(StandardCharsets.UTF_8));
        bufferedOutputStream.close();
    }
}
