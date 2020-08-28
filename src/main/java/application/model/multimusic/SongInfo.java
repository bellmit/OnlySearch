package application.model.multimusic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SongInfo {
    private String songSrc;//音乐链接
    private String title;//音乐标题
    private String singer;//歌手
}
