package application.service.tv;

import java.util.List;
import java.util.Map;

public interface TVPlaySearchServiceInterface {

    public void analyseBigData(Map<String, Object> map,
                               List<Map<String, Object>> list,
                               int pageIndex);
}
