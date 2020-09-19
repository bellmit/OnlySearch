package application.mybatis.mappers;

import application.mybatis.model.IQiYi;
import application.mybatis.model.TenXun;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: wtl
 * @License: (C) Copyright 2020. Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020-09-15 5:46
 * @Version: 1.0
 * @Description:
 */
public interface TenXunMapper {
    /**
     * 插入腾讯信息
     * @param tenXun TenXun
     * @return int
     */
    @Insert("insert into tengxun(`title`,`href`,`imgSrc`, `jiNumber`,`channelId`,`stars`) values(#{title},#{href},#{imgSrc},#{jiNumber},#{channelId},#{stars})")
    int insertTenXun(TenXun tenXun);

    @Delete("truncate table tengxun")
    int deleteAll();

    /**
     * 查询关键字
     * @param keyword 关键词
     * @param offset 偏移量
     * @param size 数目
     * @return List<IQiYi>
     */
    @Select("select * from tengxun where title like concat('%',#{keyword},'%') " +
            " or stars like concat('%',#{keyword},'%') limit #{offset},#{size}")
    List<TenXun> queryByKeyword(String keyword, int offset, int size);


    /**
     * 查询电视剧关键字
     * @param keyword 关键词
     * @param offset 偏移量
     * @param size 数目
     * @return List<IQiYi>
     */
    @Select("select * from tengxun where channelId = 2 and  (title like concat('%',#{keyword},'%') " +
            " or stars like concat('%',#{keyword},'%')) limit #{offset},#{size}")
    List<TenXun> queryTvByKeyword(String keyword, int offset, int size);

    /**
     * 查询电影关键字
     * @param keyword 关键词
     * @param offset 偏移量
     * @param size 数目
     * @return List<IQiYi>
     */
    @Select("select * from tengxun where channelId = 1 and  (title like concat('%',#{keyword},'%') " +
            " or stars like concat('%',#{keyword},'%')) limit #{offset},#{size}")
    List<TenXun> queryMovieByKeyword(String keyword, int offset, int size);
}
