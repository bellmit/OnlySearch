package application.model.index;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TopBaiduBuzz {
    private String hotKeyword;
    private String url;
    private long searchCount;
    private boolean isRise;
}
