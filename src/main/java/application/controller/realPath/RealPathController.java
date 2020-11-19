package application.controller.realPath;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wtl
 * @program: OnlySearch
 * @description:
 * @date 2020-04-11 06:59:33
 */
@Controller
public class RealPathController {

    @GetMapping("/wtl199201180271")
    public String real(){
        return "realPath/index";
    }

    @GetMapping("/")
    public String index(){
        return "forward:/boke/getPages/0/30";
    }

}
