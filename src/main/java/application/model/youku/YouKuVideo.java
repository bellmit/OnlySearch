package application.model.youku;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: 优酷实体
 * @date 2020-04-06 15:32:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class YouKuVideo {
    /**
     * 视频的图片地址
     */
    private String imgSrc;
    /**
     * 视频标题
     */
    private String title;
    /**
     * 视频链接
     */
    private String videoUrl;
}
