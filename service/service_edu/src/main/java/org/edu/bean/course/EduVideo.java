package org.edu.bean.course;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class EduVideo {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer courseId;
    private Integer chapterId;
    private String title;
    private String videoSourceId;
    private String videoOriginalName;
    private Integer sort;
    private Long playCount;
    private Integer isFree;
    private Float duration;
    private String status;
    private Long size;
    private Long version;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtUpdate;

}
