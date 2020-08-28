package application.model.cloudMusic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: 歌曲详情
 * @date 2020-04-04 14:38:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Song {
    /**
     * 歌曲id
     */
    private String songId;
    /**
     * 歌曲名
     */
    private String name;
    /**
     * 歌曲图片
     */
    private String picUrl;
    /**
     * 歌曲链接
     */
    private String songUrl;
    /**
     * 歌曲大小
     */
    private int size;
}
