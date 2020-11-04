package org.edu.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseInfoVo {

    @ApiModelProperty("课程id")
    private Integer id;
    @ApiModelProperty("讲师ID")
    private Long   teacherId;
    @ApiModelProperty("课程ID")
    private Integer subjectId;
    @ApiModelProperty("课程父ID")
    private Integer subjectParentId;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("价格")
    private BigDecimal price;
    @ApiModelProperty("总课时数")
    private Integer lessonNum;
    @ApiModelProperty("封面图片")
    private String cover;
    @ApiModelProperty("课程简介")
    private String description;
}
