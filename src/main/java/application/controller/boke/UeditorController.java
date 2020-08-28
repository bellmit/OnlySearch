package application.controller.boke;

import application.mybatis.model.Boke;
import application.service.boke.UeditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author wtl
 */
@Controller
@RequestMapping("/ueditor")
public class UeditorController {

    @Resource
    private UeditorService ueditorService;

    @RequestMapping("/config")
    public String config(String action, HttpServletRequest request, HttpServletResponse response) {
        if ("config".equalsIgnoreCase(action)) {
            return "redirect:/htmls/ueditor/jsp/config.json";
        }
        else if ("uploadimage".equalsIgnoreCase(action)) {
            return "forward:/ueditor/upload";
        }
        return null;
    }

    /**
     * 进入博客编辑的入口
     * @param editorType "update" : 更新；"edit" : 编辑
     * @return
     */
    @GetMapping("/ueditorView/{editorType}/{id}")
    public String ueditorView(@PathVariable("editorType") String editorType,
                              @PathVariable("id") int id,
                              Model model) {
        model.addAttribute("editorType",editorType);
        model.addAttribute("id",id);
        return "boke/editor";
    }

    @PostMapping("/upload")
    public @ResponseBody
    Map<String, Object> upload(MultipartFile upfile) {
        return ueditorService.upload(upfile);
    }

    @GetMapping("/getImageUrl")
    public @ResponseBody void getImageUrl(String uuid, HttpServletResponse httpServletResponse) throws Exception {
        ueditorService.getImageUrl(uuid, httpServletResponse);
    }

    @PostMapping("/uploadArticle")
    @Transactional
    @ResponseBody
    public Map<String, Object> uploadArticle(@RequestBody Boke boke) {
        return ueditorService.uploadArticle(boke);
    }

    @PostMapping("/updateArticle")
    @Transactional
    @ResponseBody
    public Map<String, Object> updateArticle(@RequestBody Boke boke) {
        return ueditorService.updateArticle(boke);
    }

}