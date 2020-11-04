package org.edu.service.front.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.edu.bean.EduComment;
import org.edu.bean.course.EduCourse;
import org.edu.mapper.CommentMapper;
import org.edu.mapper.CourseMapper;
import org.edu.service.front.FrontCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class FrontCommentServiceImpl implements FrontCommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Page<EduComment> getCourseComment(Integer start, Integer limit, Integer courseId) {
        QueryWrapper<EduComment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("course_id",courseId);
        Page<EduComment> page = new Page<>(start,limit);
        commentMapper.selectPage(page,commentQueryWrapper);
        return page;
    }

    @Override
    public void saveCourseComment(EduComment comment) {
        EduCourse eduCourse = courseMapper.selectById(comment.getCourseId());
        comment.setTeacherId(eduCourse.getTeacherId());
        commentMapper.insert(comment);
    }
}
