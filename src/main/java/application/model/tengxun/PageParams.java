package application.model.tengxun;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wtl
 * @License: (C) Copyright 2020. Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020-09-12 17:15
 * @Version: 1.0
 * @Description: page的参数列表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageParams {
    private String feature = "-1";
    private String iarea = "-1";
    private String pay = "-1";
    private String sort = "-1";
    private String year = "-1";
}
