package application.mybatis.mappers;

import application.mybatis.model.TenXun;
import application.mybatis.model.TengXunManHua;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: wtl
 * @License: (C) Copyright 2020, wtl Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020/11/18 20:12
 * @Version: 1.0
 * @Description:
 */
public interface TengXunManHuaMapper {


    /**
     * 插入一条腾讯漫画的记录
     * @param tengXunManHua 腾讯漫画
     * @return int
     */
    @Insert("insert into `tengxun_manhua`(`title`,`manHuaMainUrl`,`manHuaMainPic`,`updateTo`,`author`,`tags`,`popularity`,`description`) " +
            "values(#{title},#{manHuaMainUrl},#{manHuaMainPic},#{updateTo},#{author},#{tags},#{popularity},#{description})")
    int insertTengXunManHua(TengXunManHua tengXunManHua);

    /**
     * 清空该表
     * @return int
     */
    @Delete("truncate table `tengxun_manhua`")
    int deleteAll();

    /**
     * 查询关键字
     * @param keyword 关键词
     * @param offset 偏移量
     * @param size 数目
     * @return List<IQiYi>
     */
    @Select("select * from `tengxun_manhua` where `title` like concat('%',#{keyword},'%') " +
            " or `author` like concat('%',#{keyword},'%') limit #{offset},#{size}")
    List<TengXunManHua> queryByKeyword(String keyword, int offset, int size);
}
