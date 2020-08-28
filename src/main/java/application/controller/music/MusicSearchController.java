package application.controller.music;

import application.model.music.Music;
import application.service.music.MusicSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Controller
public class MusicSearchController {

    @Autowired
    private MusicSearchService musicSearchService;

    /**
     * 获得匹配的音乐分页列表api
     * @param keyword
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping("/getAllMatchingMusics")
    public @ResponseBody
    List<Music> getAllMatchingMusics(String keyword,
                                     int pageIndex,
                                     int pageSize) throws Exception {
        return musicSearchService.getAllMatchingMusics(keyword, pageIndex,pageSize);
    }


    @RequestMapping("/musicResult")
    public String musicResult(String keyword,
                              int pageIndex,
                              int pageSize,
                              Model model){
        model.addAttribute("keyword",keyword);
        model.addAttribute("pageIndex",pageIndex);
        model.addAttribute("pageSize",pageSize);
        return "music/musicResult";
    }

    /**
     * 转换请求url
     * @param url
     */
    @RequestMapping("/exchangeMusicsUrl")
    public void exchangeMusicsUrl(String url,
                                  HttpServletRequest request,
                                  HttpServletResponse response){
        byte [] buffer = new byte[1024];
        int length = -1;
        StringBuffer content = new StringBuffer();
        BufferedOutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedInputStream is = null;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();

            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.3");
            urlConnection.setRequestProperty("cache","0");
            is = new BufferedInputStream(urlConnection.getInputStream());
            do{
                length = is.read(buffer);
                if (length !=-1){
                    //content.append(new String(buffer,0,length,encoding));
                    outputStream.write(buffer,0,length);
                }
            }while(length!=-1);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
