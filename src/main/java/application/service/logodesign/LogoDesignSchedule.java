package application.service.logodesign;

import application.mybatis.mappers.LogoSuCaiMapper;
import application.service.feign.logodesign.LogoDesignFeign;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.Resource;

/**
 * @author wtl
 */
@Configuration
@EnableAsync
public class LogoDesignSchedule {
    private final static String BASE_URL = "http://www.uugai.com";

    private static final String []URLS = {
            "logozm.php",
            "logodw.php",
            "logorw.php",
            "logojz.php",
            "logozw.php",
            "logoyu.php",
            "logokc.php",
            "logo.php"
    };

    private final static String SUB_PATH = "logo/";

    @Resource
    private LogoDesignFeign logoDesignFeign;

    @Resource
    private LogoSuCaiMapper logoSuCaiMapper;

    //@Async
    //@Scheduled(initialDelay = 5000,fixedRate = 1000 * 60 * 60 * 24)
    public void schedule() {

    }
}
