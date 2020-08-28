package application.service.fiction;

import application.model.fiction.Fiction;
import application.mybatis.mappers.FictionMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: 小说调度器
 * @date 2020-03-21 21:01:47
 */
@Service
@Configuration      // 1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@EnableAsync
public class FictionScheduler {

    @Resource
    private FictionMapper fictionMapper;

    @Resource
    private FictionService fictionService;

    //@Async
    //@Scheduled(initialDelay = 5000,fixedRate = 1000 * 60 * 60 * 24)
    public void schedule(){
        //删除记录
        fictionMapper.deleteAll();

        for (int i=0;i<Integer.MAX_VALUE;i++){
            List<Fiction> fictions = fictionService.listFictions(i + 1);
            if (fictions.size() == 0){
                break;
            }
            for (Fiction fiction : fictions){
                fictionMapper.insertFiction(fiction);
            }
        }
    }
}
