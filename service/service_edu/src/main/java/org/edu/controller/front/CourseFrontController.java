package org.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.edu.bean.course.EduCourse;
import org.edu.commonutil.Response;
import org.edu.service.front.FrontCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("课程模块")
@RestController
@RequestMapping("/service_edu/front")
public class CourseFrontController {
    @Autowired
    private FrontCourseService courseService;


    @ApiOperation("根据条件查询课程")
    @GetMapping("/course")
    public Response queryCourseCondition(Integer start,Integer limit,Integer  subjectId,Integer subjectParentId,String order,Integer upOrDown){
        //order为排序条件，分为三个值 viewCount、gmtCreate、price
        //upordown为排序条件  0为升序 1为降序  默认为1
        Page<EduCourse> eduCoursePage = courseService.queryCourseCondition(start,limit,subjectId,subjectParentId,order,upOrDown);
        return Response.ok().data("data",eduCoursePage);
    }
}
