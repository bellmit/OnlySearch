package application.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wtl
 * @License: (C) Copyright 2020. Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020-09-15 5:37
 * @Version: 1.0
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenXun {
    private int id;
    private String title;
    private String href;
    private String imgSrc;
    private int channelId;
    private String jiNumber;
    private String stars;
}
