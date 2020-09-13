package application.service.movie;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
//@EnableScheduling   // 2.开启定时任务
//@EnableAsync
public class GetAllMovieService {

    @Resource
    private List<MovieSearchServiceInterface> movieSearchServiceInterfaces;

    public Map<String,Object> getAllResults(int pageIndex){
        Map<String,Object> map = new HashMap<>(1);
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        map.put("movieList",list);


        System.out.println(movieSearchServiceInterfaces.size());

        for (int i=0;i<movieSearchServiceInterfaces.size();i++){
            movieSearchServiceInterfaces.get(i).analyseBigData(map,list,pageIndex);
        }

        return map;
    }

}
