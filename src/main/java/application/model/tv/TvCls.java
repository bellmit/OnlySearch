package application.model.tv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TvCls implements Comparable<TvCls>{
    private int index;
    private String name;
    private String url;

    @Override
    public int compareTo(TvCls obj) {
        if (index == obj.getIndex()){
            return 0;
        }
        else if (index > obj.getIndex()){
            return 1;
        }
        return -1;
    }
}
