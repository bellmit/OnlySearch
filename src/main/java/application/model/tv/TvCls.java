package application.model.tv;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
