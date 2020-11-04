package org.edu.service.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.edu.bean.course.EduCourse;

import java.util.List;

public interface FrontCourseService {

    //根据条件查询课程
    Page<EduCourse> queryCourseCondition(Integer start, Integer limit, Integer  subjectId, Integer subjectParentId, String order, Integer UpOrDown);
}
