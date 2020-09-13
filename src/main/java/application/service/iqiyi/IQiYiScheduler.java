package application.service.iqiyi;

import application.filter.SysContext;
import application.mybatis.mappers.IQiYiMapper;
import application.mybatis.model.IQiYi;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: 爱奇艺任务调度
 * @date 2020-03-17 12:27:58
 */
@Service
@Configuration      // 1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@EnableAsync
public class IQiYiScheduler {

    @Resource
    private IQiYiService iQiYiService;

    @Resource
    private IQiYiMapper iQiYiMapper;

    private int channelId = -1;
    private int dataType = -1;
    private String finished = "";
    private String marketReleaseDateLevel = "";
    private String mode = "24";
    private String pageNumber = "";
    private String pageSize = "48";
    private String area = "";
    private String type = "";
    private String version = "";
    private String other = "";
    private String other2 = "";
    private String other3 = "";

    private int[] channelIds = new int[]{
            2, 1, 4, 3, 25, 7, 16, 10, 5, 28, 12, 17, 15, 13, 21, 26, 20, 27
    };

    private int[] dataTypes = new int[]{
            1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 2, 1, 2, 2, 2, 2, 1
    };

    @Async
    @Scheduled(cron = "0 0 0/24 * * *")
    public void schedule() {

        //删除所有记录
        iQiYiMapper.deleteAll();

        for (int i=0;i<channelIds.length;i++){
            channelId = channelIds[i];
            dataType = dataTypes[i];
            for (int j=0;j<Integer.MAX_VALUE;j++){
                pageNumber = String.valueOf(j + 1);
                Object json = iQiYiService.searchTv(String.valueOf(channelId),String.valueOf(dataType),finished,marketReleaseDateLevel,mode,pageNumber,pageSize,area,type,version,other,other2,other3);

                JSONObject jsonObject = JSONObject.fromObject(json);

                System.out.println(jsonObject);

                JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("list");

                if (jsonArray.size() == 0){
                    break;
                }
                for (int k=0;k<jsonArray.size();k++){
                    JSONObject jsonArrayJSONObject = jsonArray.getJSONObject(k);
                    iQiYiMapper.insertIQiYi(new IQiYi(
                            -1,jsonArrayJSONObject.getString("name"),
                            jsonArrayJSONObject.getString("playUrl"),
                            jsonArrayJSONObject.getString("imageUrl"),
                            (jsonArrayJSONObject.containsKey("videoCount") &&
                                    StringUtils.isNotEmpty(jsonArrayJSONObject.getString("videoCount"))) ?
                                    jsonArrayJSONObject.getString("videoCount") :
                                    jsonArrayJSONObject.getString("duration"),
                            channelId,dataType,
                            jsonArrayJSONObject.containsKey("albumId") ?
                            jsonArrayJSONObject.getString("albumId") : SysContext.BLANK_STRING,
                            jsonArrayJSONObject.toString(),
                            jsonArrayJSONObject.getString("secondInfo")
                            ));
                }

            }

        }

    }
}
