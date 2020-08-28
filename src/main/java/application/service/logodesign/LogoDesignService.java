package application.service.logodesign;

import application.filter.SysContext;
import application.mybatis.mappers.LogoSuCaiMapper;
import application.mybatis.model.Sucai;
import application.service.feign.logodesign.LogoDesignFeign;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class LogoDesignService {

    @Resource
    private LogoDesignFeign logoDesignFeign;

    @Resource
    private LogoSuCaiMapper logoSuCaiMapper;

    private final static String SUB_PATH = "logo/";

    private final static String BASE_URL = "http://www.uugai.com/";

    private final static String []urls = {
            "logo.php",
            "logozm.php",
            "logodw.php",
            "logorw.php",
            "logojz.php",
            "logozw.php",
            "logoyu.php",
            "logokc.php"
    };
    /**
     * 设计字体组合
     */
    public List<String> designZitiAndPic() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(SysContext.UPLOAD_FILE_PATH + SUB_PATH + "zitis.dat")));
        String line = null;

        List<String> list = new ArrayList<String>();
        while (null != (line = bufferedReader.readLine())){
            list.add(line);
        }
        return list;
    }

    /**
     * 获取所有image模板
     * @return
     */
    public Map<String,Object> getImageMap(){
        Map<String,Object> map = new HashMap<String, Object>();
        File dir = new File(SysContext.UPLOAD_FILE_PATH + SUB_PATH);

        File[] listFiles = dir.listFiles();
        List<String> list = new ArrayList<String>();
        map.put("sucais",list);
        for (int i=0;i<listFiles.length;i++){
            if (listFiles[i].isFile()){
                list.add(listFiles[i].getName());
            }
        }
        return map;
    }


    /**
     * 分析出首页的图片链接
     * @return
     */
    public List<String> analysisIndex(){
        List<String> list = new ArrayList<String>();
        BrowserMobProxy browserMobProxy = null;
        WebDriver webDriver = null;
        try {
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

            browserMobProxy.newHar("logo");
            webDriver.get(BASE_URL);
            WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
            webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.cc.mb20 a img")));

            Har har = browserMobProxy.getHar();
            List<HarEntry> entries = har.getLog().getEntries();
            for (HarEntry harEntry : entries){
                String url = harEntry.getRequest().getUrl();
                if (url.contains("http://www.uugai.com/img/index_img")
                && url.contains("jpg")){
                    list.add(url);
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
        return list;
    }

    /**
     * 分类logo
     * @return
     */
    public List<String> classfyLogo(){
        List<String> list = new ArrayList<String>();
        for (int i=0;i<urls.length;i++){
            list.add(urls[i].split("\\.")[0]);
        }
        return list;
    }

    public List<Sucai> getClassfySucaisByLimit(String type,
                                               int offset, int size){
        return logoSuCaiMapper.selectLikeType(type,offset,size);
    }

    public void getSucaiImage(String path,HttpServletResponse httpServletResponse) throws Exception {
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(path));
            bufferedOutputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());

            int length = -1;
            byte[] buffer = new byte[1024];

            while ((length = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, length);
            }
        } finally {
            if (null != bufferedInputStream) {
                bufferedInputStream.close();
            }
            if (null != bufferedOutputStream) {
                bufferedOutputStream.close();
            }
        }

    }
}