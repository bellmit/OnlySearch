package application.service.wechat;

import application.filter.SysContext;
import application.service.feign.wechat.WeChatServerFeign;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wtl
 */
@Service
public class MultipleServerService {

    @Resource
    private WeChatServerFeign weChatServerFeign;

    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(5,AVAILABLE_PROCESSORS * 2 + 1,
            100,TimeUnit.MINUTES,new ArrayBlockingQueue<>(5),new ThreadPoolExecutor.CallerRunsPolicy());

    @PostConstruct
    public void execute(){
        THREAD_POOL_EXECUTOR.execute(()->{
            while(true){
                String json = getAccessToken(SysContext.AppID,SysContext.AppSecret);
                System.out.println(json);
                SysContext.ACCESS_TOKEN = JSONObject.fromObject(json).getString("access_token");
                try {
                    TimeUnit.SECONDS.sleep((long) (1.8 * 60 * 60 * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 获取access_token
     * @param appId appId
     * @param appSecret appSecret
     * @return String
     */
    private String getAccessToken(String appId,String appSecret){
        return weChatServerFeign.getAccessToken(appId, appSecret);
    }
}
