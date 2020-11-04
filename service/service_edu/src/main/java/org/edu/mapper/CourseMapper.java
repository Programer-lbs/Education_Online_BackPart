package org.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.edu.bean.course.EduCourse;
import org.edu.bean.vo.CourseSureInfoVo;

import java.util.List;

public interface CourseMapper extends BaseMapper<EduCourse> {
    //课程信息确认
    CourseSureInfoVo sureCourseInfo(Integer courseId);

    //按条件查询课程列表
    List<CourseSureInfoVo> queryCourse(String keyword);
}
