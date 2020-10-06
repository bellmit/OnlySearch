package application.controller.fiction;

import application.model.fiction.Fiction;
import application.service.fiction.FictionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: 小说接口
 * @date 2020-03-21 13:16:41
 */
@Controller
@RequestMapping("/fiction")
public class FictionController {

    @Resource
    private FictionService fictionService;

    @GetMapping("/fiction")
    public String fiction() {
        return "fiction/index";
    }

    @GetMapping("/listFictions/{pageIndex}")
    public @ResponseBody
    List<Fiction> listFictions(@PathVariable("pageIndex") int pageIndex) {
        return fictionService.listFictions(pageIndex);
    }

    @GetMapping("/fictionSearchResult")
    public String fictionSearchResult(String keyword, Model model) {
        model.addAttribute("keyword", keyword);
        return "fiction/searchPage";
    }

    @GetMapping("/queryByKeyword")
    public @ResponseBody
    List<Fiction> queryByKeyword(String keyword, int pageIndex) {
        return fictionService.queryByKeyword(keyword, pageIndex);
    }

    @GetMapping("/showChapter/{id}")
    public @ResponseBody
    List<Map<String, Object>> showChapter(@PathVariable("id") String id) {
        return fictionService.showChapter(id);
    }


    @GetMapping("/getChapterCatalog/{id}/{chapterId}")
    public @ResponseBody Map<String,Object> getChapterCatalog(
            @PathVariable("id") String id,
            @PathVariable("chapterId") String chapterId) {
        return fictionService.getChapterCatalog(id, chapterId);
    }

    @GetMapping("/chapter/{id}")
    public String chapter(@PathVariable("id") String id,Model model){
        model.addAttribute("id",id);
        return "fiction/chapter";
    }
}
