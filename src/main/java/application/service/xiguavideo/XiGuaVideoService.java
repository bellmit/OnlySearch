package application.service.xiguavideo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

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


    public static void main(String[] args) throws Exception {
        System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY,"D:/mine/geckodriver.exe");

        FirefoxOptions firefoxOptions = new FirefoxOptions();

        //firefoxOptions.setHeadless(true);

        FirefoxDriver firefoxDriver = new FirefoxDriver(firefoxOptions);


        firefoxDriver.manage().window().maximize();

        firefoxDriver.get("https://xdclass.net");

        firefoxDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        WebElement webElement = firefoxDriver.findElementByCssSelector(".list_item > li:nth-child(1)");
        Actions actions = new Actions(firefoxDriver);

        actions.moveToElement(webElement).perform();

        WebDriverWait webDriverWait = new WebDriverWait(firefoxDriver,10,1000);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".base > div:nth-child(3) > a:nth-child(1)")));

        WebElement elementByCssSelector = firefoxDriver.findElementByCssSelector(".base > div:nth-child(3) > a:nth-child(1)");

        elementByCssSelector.click();

        TimeUnit.SECONDS.sleep(30);

        //firefoxDriver.quit();
    }
}
