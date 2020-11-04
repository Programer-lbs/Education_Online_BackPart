package org.edu.controller.front;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.edu.commonutil.Response;
import org.edu.service.front.FrontTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api("客户端讲师模块")
@RestController
@RequestMapping("/service_edu/front")
public class TeacherFrontController {

    @Autowired
    private FrontTeacherService frontTeacherService;


    @ApiOperation("客户端查询教师")
    @GetMapping("/teachers")
    public Response getTeachers(@RequestParam("start") Integer start,
                                @RequestParam("limit") Integer limit, String keyword){
        Map<String, Object> map = frontTeacherService.listTeacher(start, limit, keyword);
        if(map==null){
            return Response.ok().data("message","未查询到相关信息");
        }
        return Response.ok().data("data",map);
    }
}
