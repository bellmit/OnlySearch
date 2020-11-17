package application.controller.tengxun_manhua;

import application.service.tengxun_manhua.TengXunManHuaService;
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
 * @author: wtl
 * @License: (C) Copyright 2020, wtl Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020/11/15 11:29
 * @Version: 1.0
 * @Description:
 */
@Controller
@RequestMapping("tengxun_manhua")
public class TengXunManHuaController {

    @Resource
    private TengXunManHuaService tengXunManHuaService;

    /**
     * 分页获取全部漫画的列表
     * @param classify 分类
     * @param pageIndex 页码
     * @return List<Map<String,Object>>
     */
    @GetMapping("/getTengXunManHuaList/{classify}/{pageIndex}")
    public @ResponseBody List<Map<String,Object>> getTengXunManHuaList(@PathVariable String classify,@PathVariable int pageIndex){
        return tengXunManHuaService.getTengXunManHuaList(classify,pageIndex);
    }

    /**
     * 获取漫画的详情根据漫画id
     * @param id id唯一标识
     * @return Map<String,Object>
     */
    @GetMapping("/getTengXunManHuaInfoById/{id}")
    public @ResponseBody Map<String,Object> getTengXunManHuaInfoById(@PathVariable String id){
        return tengXunManHuaService.getTengXunManHuaInfoById(id);
    }

    /**
     * 计算出章节获取图片所需要的window.nonce、window.DATA
     * @param id 唯一标识
     * @param cid cid
     * @return Map<String,Object>
     */
    @GetMapping("/computeNonceAndData/{id}/{cid}")
    public @ResponseBody
    String computeNonceAndData(@PathVariable String id,@PathVariable String cid) throws Exception {
        return tengXunManHuaService.computeNonceAndData(id, cid);
    }

    @GetMapping("/tengXunManHuaIndex/{classify}")
    public String tengXunManHuaIndex(@PathVariable String classify, Model model){
        model.addAttribute("classify",classify);
        return "tengxun_manhua/index";
    }

    @GetMapping("/showChapter/{id}/{chapterId}")
    public String showChapter(@PathVariable String id ,@PathVariable String chapterId,Model model){
        model.addAttribute("id",id);
        model.addAttribute("chapterId",chapterId);
        return "tengxun_manhua/showChapter";
    }
}
