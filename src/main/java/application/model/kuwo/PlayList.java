package application.model.kuwo;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;

/**
 * @author Administrator
 * @title: PlayList
 * @projectName OnlySearch
 * @description: TODO
 * @date 2020/6/616:51
 */
@Data
@Builder
public class PlayList {
    private String id;
    private String imgSrc;
    private String name;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
