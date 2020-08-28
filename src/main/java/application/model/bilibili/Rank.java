package application.model.bilibili;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 排名类
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rank {
    /**
     * 图片地址
     */
    private String imgSrc;
    /**
     * 视频播放地址
     */
    private String videoHref;
    /**
     * 标题
     */
    private String title;
    /**
     * 浏览量
     */
    private String scanCount;
    /**
     * 作者
     */
    private String author;
    /**
     * 综合得分
     */
    private int score;
}
