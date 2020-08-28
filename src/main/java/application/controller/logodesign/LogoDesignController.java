package application.controller.logodesign;

import application.mybatis.model.Sucai;
import application.service.logodesign.LogoDesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/logo")
public class LogoDesignController {

    @Autowired
    private LogoDesignService logoDesignService;

    @GetMapping("/getImageMap")
    public @ResponseBody
    Map<String,Object> getImageMap(){
        return logoDesignService.getImageMap();
    }

    @GetMapping("/index")
    public String index(){
        return "logodesign/index";
    }

    @GetMapping("/analysisIndex")
    public @ResponseBody List<String> analysisIndex(){
        return logoDesignService.analysisIndex();
    }

    @GetMapping("/classfyLogo")
    public @ResponseBody List<String> classfyLogo(){
        return logoDesignService.classfyLogo();
    }


    @GetMapping("/logoContent/{type}")
    public String logoContent(@PathVariable("type") String type,
                              Model model){
        model.addAttribute("type",type);
        return "logodesign/logoContent";
    }

    @GetMapping("/getClassfySucaisByLimit/{type}/{offset}/{size}")
    public @ResponseBody List<Sucai> getClassfySucaisByLimit(@PathVariable("type") String type,
                                                             @PathVariable("offset") int offset,
                                                             @PathVariable("size") int size){
        return logoDesignService.getClassfySucaisByLimit(type,offset,size);
    }


    @GetMapping("/getSucaiImage")
    public void getSucaiImage(@RequestParam("path") String path, HttpServletResponse httpServletResponse) throws Exception{
        logoDesignService.getSucaiImage(path,httpServletResponse);
    }

    @GetMapping("/designZitiAndPic")
    public @ResponseBody List<String> designZitiAndPic() throws Exception{
        return logoDesignService.designZitiAndPic();
    }

    @GetMapping("/zitis/{ziTi}")
    public String ziti(@PathVariable("ziTi")String ziTi,Model model) throws Exception{
        model.addAttribute("ziTi",ziTi);
        return "logodesign/zitis";
    }
}
