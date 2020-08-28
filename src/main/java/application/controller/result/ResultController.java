package application.controller.result;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Lazy
@Scope("singleton")
public class ResultController {


    @RequestMapping("/result")
    public String result(String keyword, int pageIndex, Model model){
        model.addAttribute("keyword",keyword);
        model.addAttribute("pageIndex",pageIndex);
        return "result/result";
    }


}
