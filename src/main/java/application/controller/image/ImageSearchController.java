package application.controller.image;


import application.service.image.ImageSearchService;
import application.utils.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ImageSearchController {

    @Resource(name = "imageSearchService")
    private ImageSearchService imageSearchService;

    /**
     * 获得所有匹配关键字的分页图片api
     * @param keyword
     * @param offset
     * @param pageSize
     * @return
     */
    @RequestMapping("/getAllMatchingImages")
    public @ResponseBody
    Object getAllMatchingImages(String keyword,
                                     int offset,
                                     int pageSize){
        return imageSearchService.getAllMatchingImages(keyword, offset, pageSize);
    }

    @RequestMapping("/imageResult")
    public String imageResult(String keyword,
                              int pageIndex,
                              int pageSize,
                              Model model,
                              HttpServletResponse response){
        response.addCookie(new Cookie("sessionId", UUID.getUUID()));
        model.addAttribute("keyword",keyword);
        model.addAttribute("pageIndex",pageIndex);
        model.addAttribute("pageSize",pageSize);
        return "image/imageResult";
    }
}
