package application.model.cloudMusic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: SongList
 * @date 2020-04-04 12:59:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SongList {
    /**
     * 图片url
     */
    private String imgSrc;
    /**
     * 播放量
     */
    private String playCount;
    /**
     * 标题
     */
    private String title;
    /**
     * 播放单id
     */
    private String playListId;
    /**
     * 作者
     */
    private String author;
}
