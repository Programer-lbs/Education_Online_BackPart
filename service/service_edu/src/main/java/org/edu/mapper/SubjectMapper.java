package org.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.edu.bean.EduSubject;

public interface SubjectMapper  extends BaseMapper<EduSubject> {

    //查询是否存在课程分类信息
    EduSubject existSubject(@Param("title")String title,@Param("pid")Integer pid);
}
