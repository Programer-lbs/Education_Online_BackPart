package org.edu.bean.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseSureInfoVo {

    private Integer id;
    private String cover;
    private String title;
    private BigDecimal price;
    private String oneSubject;
    private String twoSubject;
    private String description;
    private String name;

}
