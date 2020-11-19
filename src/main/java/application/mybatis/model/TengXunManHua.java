package application.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wtl
 * @License: (C) Copyright 2020, wtl Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020/11/18 20:13
 * @Version: 1.0
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TengXunManHua {
    private int id;
    private String title;
    private String manHuaMainUrl;
    private String manHuaMainPic;
    private String updateTo;
    private String author;
    private String tags;
    private String popularity;
    private String description;
}
