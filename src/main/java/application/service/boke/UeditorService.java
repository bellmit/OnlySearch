package application.service.boke;

import application.filter.SysContext;
import application.mybatis.mappers.BoKeMapper;
import application.mybatis.model.Boke;
import application.utils.FileUploadAndDownloadUtil;
import application.utils.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wtl
 */
@Service
public class UeditorService {

    @Resource
    private BoKeMapper boKeMapper;

    public Map<String, Object> upload(MultipartFile upfile) {
        Map<String, Object> map = FileUploadAndDownloadUtil.upload(upfile);
        Boke boke = new Boke();
        boke.setPicPath(map.get("uuid").toString());
        if (boKeMapper.insertImage(boke)) {
            map.put("url", "/ueditor/getImageUrl?uuid=" + map.get("uuid").toString() + ".png");
        }
        return map;
    }

    public void getImageUrl(String uuid, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.getOutputStream().write(FileUploadAndDownloadUtil.getDownloadFile(uuid));
        httpServletResponse.getOutputStream().close();
    }

    public Map<String, Object> uploadArticle(@RequestBody Boke boke) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            /*
             * 然后对数据库进行插入、更新操作
             */
            Date date = new Date();
            boke.setUploadDate(date);
            boke.setUpdateTime(date);
            boolean bool = boKeMapper.insertBoke(boke);
            boke.setArticleId(boke.getId());
            if (bool) {
                if (null != boke.getPicUrls()) {
                    for (int i = 0; i < boke.getPicUrls().size(); i++) {
                        System.out.println(boke.getPicUrls().get(i));
                        boke.setPicPath(boke.getPicUrls().get(i).split("=")[1]);
                        boKeMapper.updateImage(boke);
                    }
                }

            }
            map.put("state", "SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", "FAILED");
        }
        return map;
    }

    public Map<String, Object> updateArticle(@RequestBody Boke boke) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Boke oldBoKe = boKeMapper.getBokeById(boke.getId());
            boKeMapper.deleteBoKeImages(oldBoKe.getArticleId());


            /**
             * 然后对数据库进行插入、更新操作
             */
            Date date = new Date();
            boke.setUpdateTime(date);
            boolean bool = boKeMapper.updateBoke(boke);
            boke.setArticleId(boke.getId());
            if (bool) {
                if (null != boke.getPicUrls()) {
                    for (int i = 0; i < boke.getPicUrls().size(); i++) {
                        System.out.println(boke.getPicUrls().get(i));
                        boke.setPicPath(boke.getPicUrls().get(i).split("=")[1]);
                        boKeMapper.updateImage(boke);
                    }
                }

            }
            map.put("state", "SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", "FAILED");
        }
        return map;
    }
}
