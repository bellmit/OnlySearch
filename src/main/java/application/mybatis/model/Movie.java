package application.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Movie implements Serializable {
    private int id;
    private String title;
    private String href;
    private String imgSrc;
    private String jiNumber;
    private String subTitle;

    private static final long serialVersionUID = 1L;
}