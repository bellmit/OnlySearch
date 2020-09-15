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
    private String channel = "tv";
    private String feature = "-1";
    private String iarea = "-1";
    private String pay = "-1";
    private String sort = "-1";
    private String year = "-1";
    private String charge = "-1";
    private String itype = "-1";
    private String characteristic = "-1";
    private String ipay = "-1";
    private String iyear = "-1";
    private String source = "1";
    private String exclusive = "1";
    private String plot_aspect = "-1";
    private String language = "-1";
    private String anime_status = "-1";
    private String itrailer = "-1";
    private String cuisine_style = "-1";
    private String food_itype = "2887";
    private String iaspect = "-1";
    private String icolumn = "-1";
    private String icelebrity = "-1";
    private String category = "-1";
    private String time = "-1";
    private String class_type = "-1";
    private String column = "-1";
    private String icompany = "-1";
}
