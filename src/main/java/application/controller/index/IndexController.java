package application.controller.index;

import application.model.index.TopBaiduBuzz;
import application.service.index.IndexService;
import application.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private IndexService indexService;

    @RequestMapping("/index")
    public String index(HttpServletResponse response){
        response.addCookie(new Cookie("sessionId", UUID.getUUID()));
        return "index";
    }

    @GetMapping("/getHao123TopWeatherAndCalendar")
    public @ResponseBody Map<String,String> getHao123TopWeatherAndCalendar(HttpServletRequest request) throws Exception {
        return indexService.getHao123TopWeatherAndCalendar(request);
    }

    @GetMapping("/getBaiduTopBuzzList")
    public @ResponseBody
    List<TopBaiduBuzz> getBaiduTopBuzzList() throws Exception{
        return indexService.getBaiduTopBuzzList();
    }

    @GetMapping("/recommend")
    public @ResponseBody List<Map<String,Object>> recommend(int pageNumber,int pageSize){
        return indexService.recommend(pageNumber, pageSize);
    }
}
