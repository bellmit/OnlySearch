package application.service.tengxun_manhua;

import application.mybatis.mappers.TengXunManHuaMapper;
import application.mybatis.model.TengXunManHua;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: wtl
 * @License: (C) Copyright 2020, wtl Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020/11/18 20:27
 * @Version: 1.0
 * @Description:
 */
@Configuration
public class TengXunManHuaScheduler {

    @Resource
    private TengXunManHuaService tengXunManHuaService;

    @Resource
    private TengXunManHuaMapper tengXunManHuaMapper;

    @Async
    @Scheduled(cron = "0 0 0 15 * ?")
    public void schedule(){
        //清空表
        tengXunManHuaMapper.deleteAll();


        for (int i=0;i<Integer.MAX_VALUE;i++){
            List<Map<String, Object>> tengXunManHuaList = tengXunManHuaService.getTengXunManHuaList("ALL", i + 1);
            if (tengXunManHuaList.size() == 0){
                break;
            }
            tengXunManHuaList.forEach(map -> {
                TengXunManHua tengXunManHua = TengXunManHua.builder()
                        .title(String.valueOf(map.get("title")))
                        .manHuaMainUrl(String.valueOf(map.get("manHuaMainUrl")))
                        .manHuaMainPic(String.valueOf(map.get("manHuaMainPic")))
                        .updateTo(String.valueOf(map.get("updateTo")))
                        .author(String.valueOf(map.get("author")))
                        .tags(JSON.toJSONString(map.get("tags")))
                        .popularity(String.valueOf(map.get("popularity")))
                        .description(String.valueOf(map.get("description")))
                        .build();
                tengXunManHuaMapper.insertTengXunManHua(tengXunManHua);

                System.out.println(JSON.toJSONString(tengXunManHua));
            });

            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
