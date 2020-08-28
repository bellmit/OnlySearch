package application.model.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Music {
    private String rid;
    private String album;
    private String picUrl;
    private String singer;
    private String musicUrl;
    private int total;
}
