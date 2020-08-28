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
 * 喜马拉雅传参数据类型
 */
public class XimalayaDataBody {
    private String kw;
    private boolean paidFilter;
    private int size;
}
