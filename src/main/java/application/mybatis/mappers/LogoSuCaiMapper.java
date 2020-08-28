package application.mybatis.mappers;

import application.mybatis.model.Sucai;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LogoSuCaiMapper {

    @Select("select * from sucai limit #{start},#{size}")
    List<Sucai> getSucaiByLimit(int start, int limit);

    @Insert("insert into sucai(sucaiUrl) values(#{sucaiUrl})")
    int insertSucai(Sucai sucai);

    @Delete("truncate table sucai")
    int deleteAllSucai();

    @Select("select * from sucai where sucaiUrl like concat('%',#{type},'%') limit #{offset},#{size}")
    List<Sucai> selectLikeType(String type, int offset, int size);
}
