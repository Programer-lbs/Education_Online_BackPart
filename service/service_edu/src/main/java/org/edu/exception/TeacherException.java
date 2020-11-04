package org.edu.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  //生成构造函数
@NoArgsConstructor  //生成无参构造函数
public class TeacherException extends RuntimeException{
    private String msg;
}
