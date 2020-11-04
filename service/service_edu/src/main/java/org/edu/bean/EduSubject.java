package org.edu.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class EduSubject {

    @ExcelProperty(value = "课程序号",index = 0)
    private Integer id;

    @ExcelProperty(value = "课程名",index = 1)
    private String title;

    @ExcelProperty(value = "类别",index = 2)
    private Integer parentId;

    @ExcelProperty(value = "序号",index = 3)
    private Integer sort;

    @ExcelProperty(value = "创建时间",index = 4)
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ExcelProperty(value = "修改时间",index = 5)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtUpdate;

}
