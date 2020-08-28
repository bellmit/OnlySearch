package application.model.cartoon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartoonPageContent {
    private String url;//页面url
    private String imgUrl;//图片url
    private String title;//名称
    private String jiNumber;//更新至集数
    private int pageSize;//该系列的页数
    private List<Integer> indexs;//索引
}
