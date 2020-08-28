package application.controller.audio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URLEncoder;

@Controller
public class AudioPlayController {

    @GetMapping("/playWithThreePart")
    public String playWithThreePart(String playUrl,String name, Model model) throws Exception {
        String url = URLEncoder.encode(playUrl,"utf-8");
        model.addAttribute("url",url);
        model.addAttribute("name",name);
        return "play/play";
    }

    @GetMapping("/playMovieWithThreePart")
    public String playMovieWithThreePart(String playUrl,String name, Model model) throws Exception {
        String url = URLEncoder.encode(playUrl,"utf-8");
        model.addAttribute("url",url);
        model.addAttribute("name",name);
        return "play/playMovie";
    }
}
