package org.edu.service.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.edu.bean.EduComment;



public interface FrontCommentService {

    //分页查看课程评论
    Page<EduComment> getCourseComment(Integer start, Integer limit, Integer courseId);

    //添加课程评论
    void saveCourseComment(EduComment comment);
}
