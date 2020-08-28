package application.model.fiction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: 章节
 * @date 2020-03-22 14:36:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Chapter {
    private String chapterUrl;
    private String name;
}
