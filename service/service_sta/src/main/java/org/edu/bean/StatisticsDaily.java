package org.edu.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class StatisticsDaily {

    private Long id;
    private String dateCalculated;
    private Integer registerNum;
    private Integer loginNum;
    private Integer videoViewNum;
    private Integer courseNum;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    //别名字段，用于数据查询
    private Integer data;


}
