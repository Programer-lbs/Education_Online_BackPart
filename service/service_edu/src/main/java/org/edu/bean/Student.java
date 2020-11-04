package org.edu.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @ExcelProperty(value = "学号",index = 0)
    private Integer sno;
    @ExcelProperty(value = "姓名",index = 1)
    private String sname;
}
