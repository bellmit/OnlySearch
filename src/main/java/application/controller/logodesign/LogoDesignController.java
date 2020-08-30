package application.controller.logodesign;

import application.service.logodesign.LogoDesignService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author wtl
 */
@Controller
@RequestMapping("/logo")
public class LogoDesignController {

    @Resource
    private LogoDesignService logoDesignService;

    /**
     * 获取首页图片列表
     * @return List<String>
     */
    @GetMapping("/getIndexImages")
    public @ResponseBody List<String> getIndexImages(){
        return logoDesignService.analysisIndex();
    }

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("indexImgList",logoDesignService.analysisIndex());
        return "logodesign/index";
    }

    /**
     * 获取分页的文字类型
     * @param pageIndex 页码
     * @return List<String>
     */
    @GetMapping("/getWenziListByPage/{pageIndex}")
    public @ResponseBody List<String> getWenziListByPage(@PathVariable int pageIndex){
        return logoDesignService.getZitiListByPage(pageIndex);
    }

    @GetMapping("/logoContent/{word}")
    public String logoContent(@PathVariable String word, Model model){
        model.addAttribute("word",word);
        return "logodesign/logoContent";
    }

    /**
     * 获取所有字体样式
     * @return List<String>
     */
    @GetMapping("/getAllZitiList")
    public @ResponseBody List<String> getAllZitiList(){
        return logoDesignService.getAllZitiList();
    }

    /**
     * 获取图片根据logo类型
     * @param ziti 字体
     * @param logodw logo类型
     * @param pageIndex 页码
     * @return List<String>
     */
    @GetMapping("/getImagesByLogoStyle/{ziti}/{logodw}/{pageIndex}")
    public @ResponseBody List<String> getImagesByLogoStyle(
            @PathVariable String ziti,
            @PathVariable String logodw,
            @PathVariable int pageIndex){
        return logoDesignService.getImagesByLogoStyle(ziti, logodw, pageIndex);
    }

    /**
     * 获取log_img_sc
     * @param ziti 字体
     * @param tu 图片
     * @param textColor 颜色
     * @param txt 文字
     * @return byte[]
     */
    @GetMapping("/getLogImageSrc/{ziti}/{tu}/{textColor}/{txt}")
    public @ResponseBody void getLogImageSrc(
            @PathVariable String ziti,
            @PathVariable String tu,
            @PathVariable String textColor,
            @PathVariable String txt,
            HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setHeader("Content-Type","image/png");
        byte[] bytes = logoDesignService.getLogImageSrc(ziti, tu, textColor, txt);
        httpServletResponse.getOutputStream().write(bytes);
    }
}
