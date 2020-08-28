package application.service.logodesign;

import application.filter.SysContext;
import application.mybatis.mappers.LogoSuCaiMapper;
import application.mybatis.model.Sucai;
import application.service.feign.logodesign.LogoDesignFeign;
import application.utils.FileUploadAndDownloadUtil;
import application.utils.UUID;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
//@EnableScheduling   // 2.开启定时任务
//@EnableAsync
public class LogoDesignScheduleService {
    private final static String BASE_URL = "http://www.uugai.com";

    private final static String []urls = {
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

    @Autowired
    private LogoDesignFeign logoDesignFeign;

    @Autowired
    private LogoSuCaiMapper logoSuCaiMapper;

    //@Async
    //@Scheduled(initialDelay = 5000,fixedRate = 1000 * 60 * 60 * 24)
    public void schedule() {
        BrowserMobProxy browserMobProxy = null;
        WebDriver webDriver = null;
        try {
            //删除旧的文件列表
            File dirFile = new File(SysContext.UPLOAD_FILE_PATH + SUB_PATH);
            if (!dirFile.exists()){
                dirFile.mkdirs();
            }
            File[] listFiles = dirFile.listFiles();
            for (int i=0;i<listFiles.length;i++){
                if (listFiles[i].isFile()){
                    listFiles[i].delete();
                }
            }

            //清空表
            logoSuCaiMapper.deleteAllSucai();

            /**
             * 处理字体url，整理出所有字体的url接口
             */
            String html = logoDesignFeign.getZitiList();
            Document document = Jsoup.parse(html);
            Elements buttons = document.select("button");
            StringBuilder stringBuilder = new StringBuilder();
            for (Element element : buttons){
                String zitiUrl = BASE_URL + "/logoa/" + element.selectFirst("img").attr("src") +
                        System.getProperty("line.separator");
                stringBuilder.append(zitiUrl);
            }
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(SysContext.UPLOAD_FILE_PATH + SUB_PATH + "zitis.dat"));

            bufferedOutputStream.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
            bufferedOutputStream.close();

            browserMobProxy = new BrowserMobProxyServer();
            browserMobProxy.start(0);

            Proxy seleniumProxy = ClientUtil.createSeleniumProxy(browserMobProxy);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setHeadless(true);
            chromeOptions.addArguments("--no-sandbox",
                    "--disable-gpu",
                    "--disable-dev-shm-usage");
            chromeOptions.setProxy(seleniumProxy);
            webDriver = new ChromeDriver(chromeOptions);

            browserMobProxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
            browserMobProxy.setHarCaptureTypes(CaptureType.RESPONSE_CONTENT);

            for (int i=0;i<urls.length;i++){
                for (int j=0;j<Integer.MAX_VALUE;j++){
                    boolean flag = false;
                    browserMobProxy.newHar(urls[i]);
                    webDriver.get(BASE_URL + "/logoa/"+urls[i]+"?page=" + (j+1));
                    Har har = browserMobProxy.getHar();
                    List<HarEntry> entries = har.getLog().getEntries();
                    for (HarEntry harEntry : entries){
                        if (harEntry.getRequest().getUrl().contains("http://www.uugai.com/img/img")
                        && harEntry.getRequest().getUrl().contains("png")){
                            String path = SysContext.UPLOAD_FILE_PATH + SUB_PATH +
                                    urls[i].split("\\.")[0]
                                    + "_" + UUID.getUUID() + ".png";
                            FileUploadAndDownloadUtil.downloadFile(
                                    harEntry.getRequest().getUrl(),
                                    path);
                            Sucai sucai = new Sucai();
                            sucai.setSucaiUrl(path);
                            logoSuCaiMapper.insertSucai(sucai);
                            flag = true;
                        }
                    }

                    browserMobProxy.endHar();

                    if (!flag){
                        break;
                    }
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (null != browserMobProxy){
                browserMobProxy.stop();
            }
            if (null != webDriver){
                webDriver.quit();
            }
        }
    }
}
