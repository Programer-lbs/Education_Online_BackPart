package org.edu.service.front.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.edu.bean.EduTeacher;
import org.edu.bean.course.EduCourse;
import org.edu.commonutil.Response;
import org.edu.mapper.CourseMapper;
import org.edu.mapper.TeacherMapper;
import org.edu.service.front.FrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrontServiceImpl implements FrontService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CourseMapper courseMapper;


    @Cacheable(value = "index",key = "courseAndTeacher")
    @Override
    public Response indexDisplay(Integer courseNum, Integer teacherNum) {
        //查询热门课程
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("view_count");
        courseQueryWrapper.last("limit "+courseNum);
        List<EduCourse> eduCourses = courseMapper.selectList(courseQueryWrapper);

        //查询热门教师
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("sort");
        teacherQueryWrapper.last("limit "+teacherNum);
        List<EduTeacher> eduTeachers = teacherMapper.selectList(teacherQueryWrapper);
        return Response.ok().data("course",eduCourses).data("teacher",eduTeachers);
    }
}
