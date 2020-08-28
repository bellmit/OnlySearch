package application.model.fiction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: 小说类
 * @date 2020-03-21 17:57:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Fiction {
    public static final String STATUS_SERIAL = "连载中";
    public static final String STATUS_FINISH = "完结";
    /**
     * 图片链接
     */
    private String imgSrc;
    /**
     * 小说名称
     */
    private String title;
    /**
     * 链接
     */
    private String href;
    /**
     * 作者
     */
    private String author;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 简介
     */
    private String introduction;
    /**
     * 状态：
     * STATUS_SERIAL : 连载中
     * STATUS_FINISH : 完结
     */
    private String status;
}
