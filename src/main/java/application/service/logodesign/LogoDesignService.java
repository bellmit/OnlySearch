package application.service.logodesign;

import application.filter.SysContext;
import application.mybatis.mappers.LogoSuCaiMapper;
import application.service.feign.logodesign.LogoDesignFeign;
import feign.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wtl
 */
@Service
public class LogoDesignService {

    @Resource
    private LogoDesignFeign logoDesignFeign;

    @Resource
    private LogoSuCaiMapper logoSuCaiMapper;

    private static final String BASE_URL = "http://www.uugai.com/";

    /**
     * 获取首页图片列表
     * @return List<String>
     */
    public List<String> analysisIndex(){
        List<String> imgList = new ArrayList<>();

        String html = logoDesignFeign.index();
        Document document = Jsoup.parse(html);
        Elements imgElements = document.select("div.cc.mb20 > a > img");

        imgElements.forEach(element -> {
            imgList.add(BASE_URL + element.attr("src"));
        });
        return imgList;
    }

    /**
     * 获取分页的文字类型
     * @param pageIndex 页码
     * @return List<String>
     */
    public List<String> getZitiListByPage(int pageIndex){

        List<String> ziTiList = new ArrayList<>();

        String html = logoDesignFeign.getWenziListByPage(pageIndex);
        Document document = Jsoup.parse(html);
        Elements elements = document.select("div.wt_zitiboy button");

        elements.forEach(element -> {
            ziTiList.add(element.attr("value"));
        });

        return ziTiList;
    }


    /**
     * 获取所有字体样式
     * @return List<String>
     */
    public List<String> getAllZitiList(){
        List<String> zitiList = new ArrayList<>();

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            List<String> zitiListByPage = getZitiListByPage(i + 1);
            if (zitiListByPage.size() == 0){
                break;
            }
            else{
                zitiList.addAll(zitiListByPage);
            }
        }

        return zitiList;
    }

    /**
     * 获取图片根据logo类型
     * @param ziti 字体
     * @param logodw logo类型
     * @param pageIndex 页码
     * @return List<String>
     */
    public List<String> getImagesByLogoStyle(String ziti,String logodw,int pageIndex){
        List<String> imageList = new ArrayList<>();
        String html = logoDesignFeign.getImagesByLogoStyle("zitia=" + ziti + ";", logodw, pageIndex);
        Document document = Jsoup.parse(html);

        Elements elements = document.select("div.ysh_a > a");

        if (elements.size() == 0){
            elements = document.select("div.yshiii > a");
        }


        elements.forEach(element -> {
            imageList.add(element.attr("href"));
        });

        return imageList;
    }

    /**
     * 获取log_img_sc
     * @param ziti 字体
     * @param tu 图片
     * @param textColor 颜色
     * @param txt 文字
     * @return byte[]
     */
    public byte[] getLogImageSrc(String ziti,String tu,String textColor,String txt) throws Exception {
        return logoDesignFeign.getLogImageSrc(
                SysContext.LOGO_DESIGN_COOKIE.replaceAll("TU",tu)
                .replaceAll("TXT",txt)
                .replaceAll("TTF",ziti)
                .replaceAll("TEXTCOLOR",textColor)
        );
    }
}