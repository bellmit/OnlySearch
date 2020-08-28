package application.model.kuwo;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;

/**
 * @author Administrator
 * @title: LeaderBoard
 * @projectName OnlySearch
 * @description: TODO
 * @date 2020/6/619:16
 */
@Data
@Builder
public class LeaderBoard {
    private int albumId;
    private String album;
    private String albumPic;
    private String artist;
    private int artistId;
    private int rid;
    private String name;
    private String pic;
    private String songTimeMinutes;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
