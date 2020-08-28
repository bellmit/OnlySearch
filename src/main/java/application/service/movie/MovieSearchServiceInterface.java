package application.service.movie;

import java.util.List;
import java.util.Map;

public interface MovieSearchServiceInterface {

    public void analyseBigData(Map<String, Object> map,
                               List<Map<String, Object>> list,
                               int pageIndex);
}
