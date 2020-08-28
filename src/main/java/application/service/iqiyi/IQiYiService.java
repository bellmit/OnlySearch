package application.service.iqiyi;

import application.mybatis.mappers.IQiYiMapper;
import application.mybatis.model.IQiYi;
import application.service.feign.iqiyi.IQiYiFeign;
import application.service.feign.tv.AiqiyiTvFeign;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wtl
 * @program: springboot
 * @description:
 * @date 2020-03-13 18:08:47
 */
@Service
public class IQiYiService {

    @Resource
    private IQiYiFeign iQiYiFeign;

    @Resource
    private AiqiyiTvFeign aiqiyiTvFeign;

    @Resource
    private IQiYiMapper iQiYiMapper;

    public Object searchTv(
            String channelId,
            String dataType,
            String finished,
            String marketReleaseDateLevel,
            String mode,
            String pageNumber,
            String pageSize,
            String area,
            String type,
            String version,
            String other,
            String other2,
            String other3) {
        return iQiYiFeign.searchTv(channelId, dataType, finished, marketReleaseDateLevel, mode, pageNumber, pageSize, area, type, version, other, other2, other3);
    }

    public String showTv(String aid,
                         int pageIndex,
                         int pageSize) {
        return aiqiyiTvFeign.showTv(aid, pageIndex, pageSize);
    }

    public String getAid(String tVid) {
        return iQiYiFeign.getAid(tVid);
    }

    public List<IQiYi> searchResult(String keyword, int offset, int size) {
        return iQiYiMapper.queryByKeyword(keyword, offset, size);
    }

    public List<IQiYi> searchTvResult(String keyword, int offset, int size) {
        return iQiYiMapper.queryTvByKeyword(keyword, offset, size);
    }

    public List<IQiYi> searchMovieResult(String keyword, int offset, int size) {
        return iQiYiMapper.queryMovieByKeyword(keyword, offset, size);
    }
}
