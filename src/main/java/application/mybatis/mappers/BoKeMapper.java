package application.mybatis.mappers;

import application.mybatis.model.Boke;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author wtl
 */
public interface BoKeMapper {

    @Insert("insert into bokePic(picPath) values(#{picPath})")
    @Options(useGeneratedKeys=true,keyColumn="id",keyProperty = "id")
    boolean insertImage(Boke boke);

    /**
     * 插入一条博客记录
     * @param boke 博客
     * @return boolean
     */
    @Insert("insert into boke(uploadDate,updateTime,title,html) values(#{uploadDate},#{updateTime},#{title},#{html})")
    @Options(useGeneratedKeys=true,keyColumn="id",keyProperty = "id")
    boolean insertBoke(Boke boke);


    @Update("update bokePic set articleId = #{articleId} where picPath = #{picPath}")
    boolean updateImage(Boke boke);

    @Select("select * from boke order by updateTime desc limit #{pageIndex},#{limit}")
    List<Boke> getBokesByLimit(int pageIndex, int limit);

    /**
     * 获取博客的数目
     * @return int
     */
    @Select("select count(1) from boke")
    int getBokesCount();

    @Select("select picPath from bokePic where articleId = #{bokeId}")
    List<String> getPicImagesByBokeId(int bokeId);

    /**
     * 根据id获取博客内容
     * @param id 主键id
     * @return Boke
     */
    @Select("select * from boke where id = #{id}")
    Boke getBokeById(int id);

    /**
     * 删除一条博客记录根据id
     * @param id 主键id
     * @return int
     */
    @Delete("delete from boke where id = #{id}")
    int deleteBoKe(int id);

    /**
     * 根据主键id更新boke的更新时间
     * @param boke 博客
     * @return boolean
     */
    @Update("update boke set `updateTime` = #{updateTime}  and `html` = #{html} where  `id` = #{id}")
    boolean updateBoke(Boke boke);

    /**
     * 删除bokePic根据articleId
     * @param articleId 文章id
     * @return boolean
     */
    @Delete("delete from bokePic where articleId = #{articleId}")
    boolean deleteBoKeImages(int articleId);

    /**
     * 根据关键字查询博客
     * @param keyword 关键词
     * @return List<Boke>
     */
    @Select("select * from boke where title like #{keyword} or html like #{keyword}")
    List<Boke> getBoKeHtmlByKeyword(String keyword);
}
