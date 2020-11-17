package application.service.index;

import application.model.index.TopBaiduBuzz;
import application.service.feign.index.Hao123Fegin;
import application.service.feign.index.IndexFegin;
import application.service.feign.index.IpFegin;
import application.service.feign.index.TopBaiduBuzzFegin;
import application.utils.UicodeBackslashUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexService {
    @Autowired
    private IndexFegin indexFegin;

    @Autowired
    private TopBaiduBuzzFegin topBaiduBuzzFegin;

    @Autowired
    private Hao123Fegin hao123Fegin;

    @Autowired
    private IpFegin ipFegin;

    public Map<String,String> getHao123TopWeatherAndCalendar(HttpServletRequest request) throws Exception {
        Map<String,String> map = new HashMap<String, String>();
        String result = indexFegin.index();
        Document document = Jsoup.parse(result);

        Element element = document.selectFirst("#weatherInfo");
        map.put("calendar",document.selectFirst("#calendar").html().replaceAll("<a ","<a target='_blank' "));

        //处理ip转地区
        InputStream inputStream = ipFegin.ip2Address(getIpAddress(request)).body().asInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = -1;
        byte [] buffer = new byte[1024];
        while ((length = inputStream.read(buffer))!=-1){
            byteArrayOutputStream.write(buffer,0,length);
        }
        inputStream.close();
        Document doc = Jsoup.parse(new String(byteArrayOutputStream.toByteArray(), "GBK"));
        map.put("city",doc.selectFirst("#c > div.bar2.f16 > b").text());
        map.put("twoDayWeathers", URLDecoder.decode(element.html(), "utf-8")
                .replaceAll("广东广州",doc.selectFirst("#c > div.bar2.f16 > b").text())
                .replaceAll("<a ","<a target='_blank' "));
        return map;
    }

    /**
     * 获取客户端IP地址
     * @param request
     * @return
     */
    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getHeader ("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getRemoteAddr ();
            if (ip.equals ("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost ();
                } catch (Exception e) {
                    e.printStackTrace ();
                }
                ip = inet.getHostAddress ();
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length () > 15) {
            if (ip.indexOf (",") > 0) {
                ip = ip.substring (0, ip.indexOf (","));
            }
        }
        return ip;
    }

    /**
     * 获取百度top搜索榜
     * @return
     */
    public List<TopBaiduBuzz> getBaiduTopBuzzList() throws Exception {
        List<TopBaiduBuzz> topBaiduBuzzes = new ArrayList<TopBaiduBuzz>();
        InputStream inputStream = topBaiduBuzzFegin.topBaiduBuzz().body().asInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = -1;
        byte [] buffer = new byte[1024];
        while ((length = inputStream.read(buffer))!=-1){
            byteArrayOutputStream.write(buffer,0,length);
        }
        inputStream.close();
        Document document = Jsoup.parse(new String(byteArrayOutputStream.toByteArray(),"GBK"));
        Elements top50 = document.select("table.list-table tr");
        for (int i=1;i<top50.size();i++){
            Element element = top50.get(i);
            if ("".equalsIgnoreCase(element.attr("class")) ||
            "hideline".equalsIgnoreCase(element.attr("class"))){
                String hotKeyword = element.selectFirst("td.keyword a.list-title").text();
                String url = element.selectFirst("td.keyword a.list-title").attr("href");
                long searchCount = Long.parseLong(element.selectFirst("td.last span").text());
                if ("icon-rise".equalsIgnoreCase(
                        element.selectFirst("td.last span").attr("class"))){
                    topBaiduBuzzes.add(new TopBaiduBuzz(
                            hotKeyword,url,searchCount,true
                    ));
                }
                else{
                    topBaiduBuzzes.add(new TopBaiduBuzz(
                            hotKeyword,url,searchCount,false
                    ));
                }
            }
        }
        return topBaiduBuzzes;
    }

    public List<Map<String,Object>> recommend(int pageNumber,int pageSize){
        String result = hao123Fegin.recommend(pageNumber, pageSize);
        result = UicodeBackslashUtil.unicodeToCn(result).replaceAll("\\\\","");
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        System.out.println(result);
        JSONArray jsonArray = JSONObject.fromObject(result).getJSONArray("data");
        for (int i=0;i<jsonArray.size();i++){
            try {
                Map<String,Object> map = new HashMap<String, Object>();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                map.put("title",jsonObject.getString("title"));
                map.put("url",jsonObject.getString("url"));
                map.put("desc",jsonObject.getString("desc").substring(0,20) + "...");
                if (jsonObject.containsKey("img0")){
                    map.put("img",jsonObject.getString("img0"));
                }
                list.add(map);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }
}
