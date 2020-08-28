package application.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Boke {
    private int id;
    private Date uploadDate;
    private Date updateTime;
    private String title;
    private String picPath;
    private List<String> picUrls;
    private int articleId;
    private String html;
    /**
     * html简介
     */
    private String htmlSmall;
    /**
     * SimpleDateFormat格式化后的日期
     */
    private String updateDate;
}
