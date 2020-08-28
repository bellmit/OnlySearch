package application.controller.boke;

import application.service.boke.BokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/boke")
public class BokeController {

    @Resource
    private BokeService bokeService;

    @GetMapping("/getgetBokesByLimits/{offset}/{limit}")
    public @ResponseBody
    Map<String, Object> getgetBokesByLimits(@PathVariable("offset") int offset, @PathVariable("limit") int limit){
        return bokeService.getBokesByLimits(offset,limit);
    }

    @GetMapping("/getPages/{offset}/{size}")
    public String getPages(@PathVariable("offset") int offset,
                           @PathVariable("size") int size, Model model){
        model.addAttribute("offset",offset);
        model.addAttribute("size",size);
        model.addAttribute("bokes",bokeService.getBokesByLimits(offset,size).get("bokesByLimits"));
        return "boke/index";
    }

    @PostMapping("/showPageInfo")
    public String showPageInfo(int id,Model model) throws Exception {
        model.addAttribute("boke",bokeService.showPageInfo(id));
        return "boke/pageInfo";
    }

    /**
     * 删除一个文章
     * @param id id
     * @return int
     */
    @GetMapping("/deleteBoKeArticle")
    public int deleteBoKeArticle(int id){
        return bokeService.deleteBoKeArticle(id);
    }

    /**
     * getBoKeHtmlByUuid
     * @param id 唯一主键
     * @return 返回的博客文章的html
     * @throws Exception ex
     */
    @GetMapping("/getBoKeHtmlByUuid")
    public @ResponseBody Map<String,Object> getBoKeHtmlByUuid(int id) throws Exception {
        Map<String,Object> map = new HashMap<>(2);
        map.put("id",id);
        map.put("boKeHtml",bokeService.getBokeHtmlById(id));
        map.put("title",bokeService.getBokeById(id).getTitle());
        return map;
    }
}
