package application.model.cartoon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartoonChapter {
    private String url;//章节url
    private String title;//章节标题
}
