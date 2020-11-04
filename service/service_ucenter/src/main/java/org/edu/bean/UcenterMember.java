package org.edu.bean;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

@Data
public class UcenterMember {

    private Long id;
    private String openid;
    private String mobile;
    private String password;
    private String nickname;
    private Integer sex;
    private Integer age;
    private String avatar;
    private String sign;
    private Integer isDisabled;
    @TableLogic
    private Integer isDeleted;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
