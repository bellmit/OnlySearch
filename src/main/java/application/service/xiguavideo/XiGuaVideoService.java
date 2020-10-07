package application.service.xiguavideo;

import application.service.feign.xiguavideo.XiGuaVideoFeign;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author: wtl
 * @License: (C) Copyright 2020
 * @Contact: 1050100468@qq.com
 * @Date: 2020/8/19 6:12
 * @Version: 1.0
 * @Description:
 */
@Service
public class XiGuaVideoService {

    @Resource
    private XiGuaVideoFeign xiGuaVideoFeign;

    /**
     * 获取西瓜视频url
     * @param xiGuaUrl 西瓜视频播放url
     * @return 解析出的真实url
     * @throws Exception 异常
     */
    public List<Map<String,Object>> getVideoUrl(String xiGuaUrl) throws Exception {
        FirefoxDriver firefoxDriver = null;
        List<Map<String,Object>> videoUrls = new ArrayList<>();
        try {
            System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY,"/mine/geckodriver");

            FirefoxOptions firefoxOptions = new FirefoxOptions();

            firefoxOptions.setHeadless(true);
            firefoxOptions.setAcceptInsecureCerts(true);
            firefoxDriver = new FirefoxDriver(firefoxOptions);

            firefoxDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            firefoxDriver.get(xiGuaUrl);

            List<WebElement> elements = firefoxDriver.findElementsByCssSelector("xg-definition.xgplayer-definition > ul li");

            System.out.println(firefoxDriver.findElementByCssSelector("html").get);
            elements.forEach(webElement->{
                Map<String,Object> map = new HashMap<>(2);
                map.put("url",webElement.getAttribute("url"));
                map.put("definition",webElement.getAttribute("definition"));
                videoUrls.add(map);
            });


            String title = firefoxDriver.findElementByCssSelector("title").getAttribute("textContent").split(" - 电影 - ")[0];
            Map<String,Object> map = new HashMap<>(1);
            map.put("title",title);
            videoUrls.add(map);

            String description = firefoxDriver.findElementByCssSelector("meta[name='description']").getAttribute("content");
            Map<String,Object> descriptionMap = new HashMap<>(1);
            descriptionMap.put("description",description);
            videoUrls.add(descriptionMap);
        }
        finally {
            assert firefoxDriver != null;
            firefoxDriver.quit();
        }
        return videoUrls;
    }


    /**
     * 获取详细的电视或电影的剧集列表和推荐列表
     * @param albumId album id
     * @return String json
     */
    public String detailPagesTvOrMovie(String albumId){
        String result = xiGuaVideoFeign.detailPagesTvOrMovie(albumId);
        System.out.println(result);
        return null;
    }
}
