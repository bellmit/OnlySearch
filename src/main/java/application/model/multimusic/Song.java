package application.model.multimusic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
/**
 * 歌曲类
 */
public class Song {
    private String songId;//音乐id
    private String songListUrl;//分类单片的url
    private String songPic;//音乐图片
    private String title;//标题
    private String singer;//歌手
    private String playCount;//播放数目
    private String platform;//平台
}
