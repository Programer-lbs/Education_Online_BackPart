package org.edu.controller;


import com.alibaba.excel.EasyExcel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.edu.bean.EduSubject;
import org.edu.bean.EduSubjects;
import org.edu.commonutil.Response;
import org.edu.config.ExcelListener;
import org.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@Api("课程分类管理")
@RestController
@RequestMapping("/service_edu")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("课程数据上传")
    @PostMapping("/subject/upload")
    public Response uploadData (MultipartFile file){
        try {
            EasyExcel.read(file.getInputStream(), EduSubject.class,new ExcelListener(subjectService)).sheet().doRead();
            return  Response.ok().message("上传成功");
        }catch (Exception e){
            return   Response.fail().message("上传失败").data("msg",e.getMessage());
        }
    }

    @ApiOperation("按级获取所有课程信息")
    @GetMapping("/subject")
    public Response allSubjects(){
        List<EduSubjects> list = subjectService.listAll();
        return Response.ok().data("items",list);
    }

    @ApiOperation("数据导出")
    @GetMapping("/subject/download")
    public void download(HttpServletResponse response) throws IOException {
        List<EduSubject> all = subjectService.all();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String filename = UUID.randomUUID().toString().replaceAll("-","").substring(0,16);
        response.setHeader("Content-disposition","attachment;filename="+filename+".xls");
        EasyExcel.write(response.getOutputStream(),EduSubject.class).sheet("课程表").doWrite(all);
    }

}
