package application.mybatis.mappers;

import application.model.fiction.Fiction;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wtl
 * @program: OnlySearch
 * @description:
 * @date 2020-03-21 21:06:34
 */
public interface FictionMapper {
    /**
     * 插入iqiyi信息
     * @param fiction
     * @return
     */
    @Insert("insert into fiction(`imgSrc`,`title`,`href`, `author`,`updateTime`,`introduction`,`status`) " +
            "values(#{imgSrc},#{title},#{href},#{author},#{updateTime},#{introduction},#{status})")
    int insertFiction(Fiction fiction);

    @Delete("truncate table fiction")
    int deleteAll();

    /**
     * 查询关键字
     * @param keyword 关键词
     * @param offset 偏移量
     * @param size 数目
     * @return List<Fiction>
     */
    @Select("select * from fiction where title like concat('%',#{keyword},'%') " +
            " or author like concat('%',#{keyword},'%') limit #{offset},#{size}")
    List<Fiction> queryByKeyword(String keyword, int offset, int size);
}
