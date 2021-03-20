package application.mybatis.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wtl
 * @License: (C) Copyright 2021, wtl Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2021/3/20/0020 15:53
 * @Version: 1.0
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Metv {
    private int id;
    private String title;
    private String starts;
    @TableField("channelId")
    private int channelId;
    @TableField("clipId")
    private String clipId;
    @TableField("playPartId")
    private String playPartId;
    private String story;
    @TableField("imgUrl")
    private String imgUrl;
    @TableField("updateInfo")
    private String updateInfo;
}
