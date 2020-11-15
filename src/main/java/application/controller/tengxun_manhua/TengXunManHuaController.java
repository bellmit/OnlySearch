package application.controller.tengxun_manhua;

import application.service.tengxun_manhua.TengXunManHuaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
     * 计算出章节获取图片所需要的window.nonce、window.DATA
     * @return Map<String,Object>
     */
    @GetMapping("/computeNonceAndData")
    public @ResponseBody
    Map<String,Object> computeNonceAndData() throws Exception {
        return tengXunManHuaService.computeNonceAndData();
    }
}
