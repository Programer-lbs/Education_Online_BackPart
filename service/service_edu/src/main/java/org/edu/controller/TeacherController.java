package org.edu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.edu.bean.EduTeacher;
import org.edu.bean.QueryTeacherVo;
import org.edu.commonutil.Response;
import org.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@Api("教师管理模块")
@RestController
@RequestMapping("/service_edu")
@CrossOrigin
public class TeacherController {


    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teachers")
    public Response getAllTeachers(){
       return Response.ok().data("items",teacherService.listTeachers());
    }


    @DeleteMapping("/teacher/{id}")
    public String deleteTeacher(@PathVariable("id")Long id){
        teacherService.deleteTeacher(id);
        return "success";
    }

    @ApiOperation("按条件分页查询")
    @PostMapping("/teacher/{current}/{limit}")
    public Response queryTeacherCondition(@PathVariable("current")Integer current, @PathVariable("limit")Integer limit,
                                          @RequestBody(required = false) QueryTeacherVo queryTeacherVo){
        Page<EduTeacher> page = teacherService.listTeacherCondition(queryTeacherVo,current,limit);
        return Response.ok().data("page",page);
    }

    @ApiOperation("查询单个教师信息")
    @GetMapping("/teacher/{id}")
    public Response queryTeacher(@PathVariable("id") Long id){
        EduTeacher one = teacherService.getOneById(id);
        if(one!=null){
            return Response.ok().data("item",one);
        }
        return Response.ok().message("未查询到该教师的信息");
    }

    @ApiOperation("修改某个教师的信息")
    @PutMapping("/teacher/update")
    public Response updateTeacher(@RequestBody EduTeacher eduTeacher){
        System.out.println(eduTeacher);
        teacherService.updateTeacher(eduTeacher);
        return Response.ok().message("修改成功");
    }

    @ApiOperation("添加教师信息")
    @PostMapping("/teacher/save")
    public Response saveTeacher(@RequestBody EduTeacher eduTeacher){
        log.info("教师",eduTeacher);
        teacherService.saveTeacher(eduTeacher);
        return Response.ok().message("添加成功");
    }

    //教师头像上传
    @PostMapping("/teacher/upload")
    public Response uploadAvatar(MultipartFile avatar){
        try {
            String avatarPath  = teacherService.uploadAvatar(avatar);
            return Response.ok().data("url",avatarPath);
        }catch (Exception e){
            return Response.fail().data("errorMsg",e.getMessage());
        }
    }
}
