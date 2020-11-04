package org.edu.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class EduTeacher {
    private Long id;
    private String name;
    private String intro;
    private String career;
    private Integer level;
    private String avatar;
    private Integer sort;

    @TableLogic  //设置逻辑删除属性
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtUpdate;
}
//
//    id                   int not null,
//            ->    name                 varchar(20),
//    ->    intro                varchar(500),
//    ->    career               varchar(500) default null,
//            ->    level                int,
//            ->    avatar               varchar(255) default null,
//            ->    sort                 int,
//            ->    is_deleted           tinyint(1) default 0,
//            ->    gmt_create           datetime,
//    ->    gmt_update           datetime,

