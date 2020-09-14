package application.service.boke;

import application.filter.SysContext;
import application.mybatis.mappers.BoKeMapper;
import application.mybatis.model.Boke;
import org.apache.ibatis.annotations.Select;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BokeService {

    @Resource
    private BoKeMapper boKeMapper;
    /**
     * 获取已经上传的文章列表，根据pageIndez和limit进行排列
     * @param offset
     * @param limit
     * @return
     */
    public Map<String,Object> getBokesByLimits(int offset, int limit){
        Map<String,Object> map = new HashMap<String, Object>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Boke> bokesByLimits = boKeMapper.getBokesByLimit(offset,limit);
        map.put("bokesByLimits",bokesByLimits);
        for (Boke boke : bokesByLimits){
            boke.setPicUrls(boKeMapper.getPicImagesByBokeId(boke.getId()));
            try {
                String html = getBokeHtmlById(boke.getId());
                boke.setHtml(html);
                boke.setUpdateDate(simpleDateFormat.format(boke.getUpdateTime().getTime()));
                String text = Jsoup.parse(html).text();
                boke.setHtmlSmall(text.length() >= 261 ? text.substring(0,260) + "..." : text + "...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        map.put("totalSize",boKeMapper.getBokesCount());
        return map;
    }

    public Boke getBokeById(int id){
        return boKeMapper.getBokeById(id);
    }

    public Boke showPageInfo(int id) throws Exception {
        Boke boke = boKeMapper.getBokeById(id);
        boke.setHtml(getBokeHtmlById(boke.getId()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boke.setUpdateDate(simpleDateFormat.format(boke.getUpdateTime().getTime()));
        return boke;
    }

    public String getBokeHtmlById(int id) throws Exception {
        Boke boke = boKeMapper.getBokeById(id);
        return boke.getHtml();
    }

    /**
     * 删除一个文章
     * @param id id
     * @return int
     */
    public int deleteBoKeArticle(int id){
        return boKeMapper.deleteBoKe(id);
    }


    /**
     * 根据关键字查询博客
     * @param keyword 关键词
     * @return List<Boke>
     */
    public List<Boke> getBoKeHtmlByKeyword(String keyword){
        return boKeMapper.getBoKeHtmlByKeyword(keyword);
    }
}
