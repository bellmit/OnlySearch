package application.model.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Image {
    private String bdImgnewsDate;
    private String fromPageTitle;
    private String fromPageTitleEnc;
    private String fromURL;
    private String fromURLHost;
    private String middleURL;
    private String thumbURL;
    private String type;
    private int width;
}
