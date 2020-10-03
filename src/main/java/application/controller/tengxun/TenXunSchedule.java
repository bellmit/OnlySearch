package application.controller.tengxun;

import application.filter.SysContext;
import application.model.tengxun.PageResult;
import application.mybatis.mappers.TenXunMapper;
import application.mybatis.model.TenXun;
import application.service.tengxun.TenXunService;
import application.utils.HttpOrHttpsUrlValidatorRequestUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author: wtl
 * @License: (C) Copyright 2020, wtl Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020-09-19 14:21
 * @Version: 1.0
 * @Description:
 */
@Configuration
public class TenXunSchedule {

    @Resource
    private TenXunService tenXunService;

    @Resource
    private TenXunMapper tenXunMapper;


    private static final String CHANNEL_TV = "tv";
    private static final String CHANNEL_MOVIE = "movie";
    private static final int PAGE_SIZE = 30;
    private static final String _ONE = "-1";

    /**
     * tv的一些字段
     */
    private int[] tvFeatures = null;
    private int[] tvIAreas = null;
    private int[] tvPays = null;
    private int[] tvYears = null;
    private int[] tvSorts = null;

    /**
     * movie的一些字段
     */
    private int[] movieITypes = null;
    private int[] movieIAreas = null;
    private int[] movieSorts = null;
    private int[] movieYears = null;
    private int[] movieCharges = null;
    private int[] movieCharacteristics = null;

    @PostConstruct
    public void init() {
        InputStream tvFileInputStream = null;
        ByteArrayOutputStream tvByteArrayOutputStream = null;

        InputStream movieFileInputStream = null;
        ByteArrayOutputStream movieByteArrayOutputStream = null;
        try {
            tvFileInputStream = TenXunSchedule.class.getClassLoader().getResourceAsStream("static/htmls/tengxun/tv.json");
            tvByteArrayOutputStream = new ByteArrayOutputStream();
            int length = -1;
            byte[] buffer = new byte[1024 * 1024];
            while ((length = tvFileInputStream.read(buffer)) != -1) {
                tvByteArrayOutputStream.write(buffer, 0, length);
            }

            String tvJson = new String(tvByteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
            JSONObject tvJsonObject = JSON.parseObject(tvJson);

            JSONArray tvFeatureArray = tvJsonObject.getJSONArray("类型|feature");
            tvFeatures = new int[tvFeatureArray.size()];
            for (int i = 0; i < tvFeatureArray.size(); i++) {
                tvFeatures[i] = tvFeatureArray.getJSONArray(i).getInteger(1);
            }
            JSONArray tvIAreaArray = tvJsonObject.getJSONArray("地区|iarea");
            tvIAreas = new int[tvIAreaArray.size()];
            for (int i = 0; i < tvIAreaArray.size(); i++) {
                tvIAreas[i] = tvIAreaArray.getJSONArray(i).getInteger(1);
            }

            JSONArray tvPayArray = tvJsonObject.getJSONArray("资费|pay");
            tvPays = new int[tvPayArray.size()];
            for (int i = 0; i < tvPayArray.size(); i++) {
                tvPays[i] = tvPayArray.getJSONArray(i).getInteger(1);
            }
            JSONArray tvYearArray = tvJsonObject.getJSONArray("年份|year");
            tvYears = new int[tvYearArray.size()];
            for (int i = 0; i < tvYearArray.size(); i++) {
                tvYears[i] = tvYearArray.getJSONArray(i).getInteger(1);
            }
            JSONArray tvSortArray = tvJsonObject.getJSONArray("排序|sort");
            tvSorts = new int[tvSortArray.size()];
            for (int i = 0; i < tvSortArray.size(); i++) {
                tvSorts[i] = tvSortArray.getJSONArray(i).getInteger(1);
            }

            movieFileInputStream = TenXunSchedule.class.getClassLoader().getResourceAsStream("static/htmls/tengxun/movie.json");
            movieByteArrayOutputStream = new ByteArrayOutputStream();
            while ((length = movieFileInputStream.read(buffer)) != -1) {
                movieByteArrayOutputStream.write(buffer, 0, length);
            }

            String movieJson = new String(movieByteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);

            JSONObject movieJsonObject = JSON.parseObject(movieJson);
            JSONArray movieIAreaArray = movieJsonObject.getJSONArray("地区|iarea");
            movieIAreas = new int[movieIAreaArray.size()];
            for (int i = 0; i < movieIAreaArray.size(); i++) {
                movieIAreas[i] = movieIAreaArray.getJSONArray(i).getInteger(1);
            }
            JSONArray movieITypeArray = movieJsonObject.getJSONArray("类型|itype");
            movieITypes = new int[movieITypeArray.size()];
            for (int i = 0; i < movieITypeArray.size(); i++) {
                movieITypes[i] = movieITypeArray.getJSONArray(i).getInteger(1);
            }
            JSONArray movieSortArray = movieJsonObject.getJSONArray("排序|sort");
            movieSorts = new int[movieSortArray.size()];
            for (int i = 0; i < movieSortArray.size(); i++) {
                movieSorts[i] = movieSortArray.getJSONArray(i).getInteger(1);
            }
            JSONArray movieYearArray = movieJsonObject.getJSONArray("年份|year");
            movieYears = new int[movieYearArray.size()];
            for (int i = 0; i < movieYearArray.size(); i++) {
                movieYears[i] = movieYearArray.getJSONArray(i).getInteger(1);
            }
            JSONArray movieChargeArray = movieJsonObject.getJSONArray("资费|charge");
            movieCharges = new int[movieChargeArray.size()];
            for (int i = 0; i < movieChargeArray.size(); i++) {
                movieCharges[i] = movieChargeArray.getJSONArray(i).getInteger(1);
            }
            JSONArray movieCharacteristicArray = movieJsonObject.getJSONArray("特色|characteristic");
            movieCharacteristics = new int[movieCharacteristicArray.size()];
            for (int i = 0; i < movieCharacteristicArray.size(); i++) {
                movieCharacteristics[i] = movieCharacteristicArray.getJSONArray(i).getInteger(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert movieFileInputStream != null;
                movieFileInputStream.close();
                assert movieByteArrayOutputStream != null;
                movieByteArrayOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Async
    @Scheduled(cron = "0 0 0 15 * ?")
    public void schedule(){
        //删除所有记录
        tenXunMapper.deleteAll();

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            try {
                List<PageResult> pageResults = searchTenXunTvPages(i * PAGE_SIZE, _ONE, _ONE, _ONE, _ONE, _ONE);
                pageResults.addAll(searchTenXunMoviePages(i * PAGE_SIZE, _ONE, _ONE, _ONE, _ONE, _ONE, _ONE));
                //处理pageResult
                pageResults.forEach(pageResult -> {
                    tenXunMapper.insertTenXun(TenXun.builder()
                            .title(pageResult.getName())
                            .href(pageResult.getUrl())
                            .imgSrc(pageResult.getPicUrl())
                            .channelId(CHANNEL_TV.equalsIgnoreCase(pageResult.getChannel()) ? 1 : 2)
                            .jiNumber(pageResult.getJiNumber())
                            .stars(pageResult.getJson())
                            .build());
                });
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public List<PageResult> searchTenXunTvPages(int offset, String feature, String iArea, String pay, String sort, String year) {
        return getPageResults(offset, sort, year, _ONE, _ONE, iArea, _ONE, CHANNEL_TV, feature, pay);
    }

    public List<PageResult> searchTenXunMoviePages(int offset, String sort, String year, String charge, String iType, String iArea, String characteristic) {
        return getPageResults(offset, sort, year, charge, iType, iArea, characteristic, CHANNEL_MOVIE, _ONE, _ONE);
    }

    private List<PageResult> getPageResults(int offset, String sort, String year, String charge, String iType, String iArea, String characteristic, String channelMovie, String one, String one2) {
        List<PageResult> pageResults = tenXunService.pageList(channelMovie, offset, PAGE_SIZE, one, iArea, one2, sort, year, charge, iType, characteristic, _ONE, _ONE, _ONE, _ONE, _ONE, _ONE, _ONE, _ONE, _ONE, _ONE, _ONE, _ONE, _ONE, _ONE, _ONE, _ONE, _ONE, _ONE);
        pageResults.forEach(pageResult -> {
            String startsHtml = HttpOrHttpsUrlValidatorRequestUtils.requestHttpsGet(pageResult.getUrl(), null, null, SysContext.ENCODING, SysContext.TEXT_HTML_MIME_TYPE);
            if (StringUtils.isNotEmpty(startsHtml)){
                Document startsDocument = Jsoup.parse(startsHtml);
                Element startEle = startsDocument.selectFirst("div[r-component=c-star]");
                pageResult.setJson(startEle.attr("r-props"));
            }
            pageResult.setChannel(channelMovie);
        });
        return pageResults;
    }
}
