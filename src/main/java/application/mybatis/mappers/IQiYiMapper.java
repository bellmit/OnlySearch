package application.mybatis.mappers;

import application.mybatis.model.IQiYi;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wtl
 * @program: OnlySearch
 * @description:
 * @date 2020-03-17 14:52:01
 */
public interface IQiYiMapper {
    /**
     * 插入iqiyi信息
     * @param iQiYi
     * @return
     */
    @Insert("insert into iqiyi(`title`,`href`,`imgSrc`, `jiNumber`,`channelId`,`dataType`,`aid`,`json`,`stars`) " +
            "values(#{title},#{href},#{imgSrc},#{jiNumber},#{channelId},#{dataType},#{aid},#{json},#{stars})")
    int insertIQiYi(IQiYi iQiYi);

    @Delete("truncate table iqiyi")
    int deleteAll();

    /**
     * 查询关键字
     * @param keyword 关键词
     * @param offset 偏移量
     * @param size 数目
     * @return List<IQiYi>
     */
    @Select("select * from iqiyi where title like concat('%',#{keyword},'%') " +
            " or stars like concat('%',#{keyword},'%') limit #{offset},#{size}")
    List<IQiYi> queryByKeyword(String keyword, int offset, int size);


    /**
     * 查询电视剧关键字
     * @param keyword 关键词
     * @param offset 偏移量
     * @param size 数目
     * @return List<IQiYi>
     */
    @Select("select * from iqiyi where channelId = 2 and  (title like concat('%',#{keyword},'%') " +
            " or stars like concat('%',#{keyword},'%')) limit #{offset},#{size}")
    List<IQiYi> queryTvByKeyword(String keyword, int offset, int size);

    /**
     * 查询电影关键字
     * @param keyword 关键词
     * @param offset 偏移量
     * @param size 数目
     * @return List<IQiYi>
     */
    @Select("select * from iqiyi where channelId = 1 and  (title like concat('%',#{keyword},'%') " +
            " or stars like concat('%',#{keyword},'%')) limit #{offset},#{size}")
    List<IQiYi> queryMovieByKeyword(String keyword, int offset, int size);
}
