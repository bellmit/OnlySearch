package application.model.cartoon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * CartoonIndexLink
 * 用来保存https://m.gufengmh8.com/search/里的链接的对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartoonIndexLink {
    private String url;
    private String name;
}
