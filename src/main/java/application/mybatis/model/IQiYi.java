package application.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author wtl
 * @program: OnlySearch
 * @description: IQiYi
 * @date 2020-03-17 15:03:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IQiYi {
    private int id;
    private String title;
    private String href;
    private String imgSrc;
    private String jiNumber;
    private int channelId;
    private int dataType;
    private String aid;
    private String json;
    private String stars;
}
