package org.edu.service.front.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.edu.bean.course.EduCourse;
import org.edu.exception.QueryException;
import org.edu.mapper.CourseMapper;
import org.edu.service.front.FrontCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FrontCourseServiceImpl implements FrontCourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Page<EduCourse> queryCourseCondition(Integer start, Integer limit, Integer subjectId,
                                                Integer subjectParentId, String order,Integer UpOrDown) {
        log.info(start+" "+limit+" "+subjectId+" "+subjectParentId+" "+order+" "+UpOrDown);
        if(start==null || limit==null){
            throw new QueryException("参数错误，分页条件不能为空");
        }
       QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
       if(subjectId!=null){
           wrapper.eq("subject_id",subjectId);
       }
       if(subjectParentId!=null){
           wrapper.eq("subject_parent_id",subjectParentId);
       }
      if(order!=null) {
          if(UpOrDown==1) {
              wrapper.orderByDesc(order);
          }
          if(UpOrDown==0) {
              wrapper.orderByAsc(order);
          }
          if(UpOrDown==null){
              wrapper.orderByDesc(order);
          }
      }
      wrapper.eq("status","Normal");

      Page<EduCourse> eduCoursePage = new Page<>(start,limit);
      courseMapper.selectPage(eduCoursePage,wrapper);
      return eduCoursePage;
    }
}
