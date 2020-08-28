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
 * 关键字提示封装
 */
public class MultiMusicKeywordSuggest {
    private String songId;//歌曲id
    private String songName;//歌曲名称
    private String singer;//歌手
}
