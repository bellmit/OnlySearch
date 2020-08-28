package application.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tv implements Serializable {
    private int id;
    private String title;
    private String href;
    private String imgSrc;
    private String jiNumber;
    private String platform;
    private String albumId;
    private static final long serialVersionUID = 1L;
}