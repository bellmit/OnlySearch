package application.controller.cartoon;

import application.model.cartoon.CartoonIndexLink;
import application.model.cartoon.CartoonSearchContent;
import application.service.cartoon.CartoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartoonController {

    @Autowired
    private CartoonService cartoonService;

    /**
     * 开放出接口：/cartoon_index
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/cartoon_index",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public List<List<CartoonIndexLink>> cartoon_index() throws Exception {
        return cartoonService.cartoonIndex();
    }

    @GetMapping(value = "/cartoonIndex",produces = {"application/json;charset=UTF-8"})
    public String cartoonIndex(Model model) throws Exception {
        //添加model到View
        model.addAttribute("data_url_s",cartoonService.cartoonIndex());
        return "cartoon/index";
    }

    @GetMapping(value = "/cartoonPages",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String,Object> cartoonPages(String prefixUrl, int pageIndex,String classfyName) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("cartoonPageContents",cartoonService.getPageContentJson(prefixUrl, pageIndex,classfyName));
        map.put("prefixUrl",prefixUrl);
        map.put("pageIndex",pageIndex);
        map.put("classfyName",classfyName);
        return map;
    }

    @GetMapping("/cartoonPageViews")
    public String cartoonPageViews(String prefixUrl, int pageIndex,String classfyName,Model model) throws Exception {
//        List<CartoonPageContent> cartoonPageContents = cartoonService.getPageContentJson(prefixUrl, pageIndex,classfyName);
//        model.addAttribute("cartoonPageContents",cartoonPageContents);
        model.addAttribute("prefixUrl",prefixUrl);
        model.addAttribute("pageIndex",pageIndex);
        model.addAttribute("classfyName",classfyName);
        return "cartoon/pageViews";
    }

    /**
     * 获取单个动漫的所有信息
     * @param url
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/cartoonContent",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String,Object> cartoonContent(String url) throws Exception {
        return cartoonService.cartoonContent(url);
    }

    @GetMapping(value = "/cartoonContentView",produces = {"application/json;charset=UTF-8"})
    public String cartoonContentView(String url,Model model){
        model.addAttribute("url",url);
        return "cartoon/pageContent";
    }

    /**
     * 获取一个章节的index图片url
     * @param chapterUrl
     * @param index
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getChapterToAImage",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String,Object> getChapterToAImage(String chapterUrl,int index) throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("imgSrc",cartoonService.getChapterToAImage(chapterUrl, index));
        return map;
    }

    /**
     * 搜索功能
     * @param keyword 关键词
     * @return
     */
    @GetMapping(value = "/searchCartoon/{keyword}")
    public @ResponseBody List<CartoonSearchContent> searchCartoon(@PathVariable String keyword){
        return cartoonService.search(keyword);
    }
}
