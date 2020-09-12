package application.model.tengxun;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wtl
 * @License: (C) Copyright 2020. Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020-09-12 18:11
 * @Version: 1.0
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResult {
    /**
     * url
     */
    private String url;
    /**
     * 图片链接
     */
    private String picUrl;
    /**
     * 片名
     */
    private String name;
    /**
     * 集数
     */
    private String jiNumber;
    /**
     * 简介
     */
    private String introduction;
}
