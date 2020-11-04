package org.edu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.edu.bean.vo.ChapterVideoRespVo;
import org.edu.bean.vo.CourseInfoVo;
import org.edu.bean.vo.CourseSureInfoVo;
import org.edu.commonutil.Response;
import org.edu.service.CourseService;
import org.edu.service.VodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api("课程信息模块")
@RestController
@RequestMapping("/service_edu")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private VodClient vodClient;

    @ApiOperation("添加课程信息")
    @PostMapping("/course")
    public Response addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        Integer courseId = courseService.saveCourseInfo(courseInfoVo);
        return Response.ok().data("courseId",courseId);
    }

    @ApiOperation("上传课程封面")
    @PostMapping("/course/upload")
    public Response uploadCover(MultipartFile cover)  {
        try {
            String url = courseService.coverUrl(cover);
            return Response.ok().data("url",url);
        }catch (Exception e){
            return Response.fail().message("上传失败").data("message",e.getMessage());
        }
    }

    @ApiOperation("获取课程的所有章节与小节信息")
    @GetMapping("/course/{courseId}")
    public Response listAllChapterVideo(@PathVariable("courseId")Integer courseId){
        List<ChapterVideoRespVo> chapterVideoRespVos = courseService.listAllChapterVideo(courseId);
        return Response.ok().data("items",chapterVideoRespVos);
    }

    @ApiOperation("获取单个课程信息")
    @GetMapping("/courseInfo/{courseId}")
    public Response getCourseInfo(@PathVariable("courseId")Integer courseId){
        CourseInfoVo courseInfo = courseService.getCourseInfo(courseId);
        return Response.ok().data("data",courseInfo);

    }

    @ApiOperation("修改课程信息")
    @PutMapping("/course")
    public Response updateCourse(@RequestBody CourseInfoVo courseInfoVo){
        Integer courseId = courseService.updateCourse(courseInfoVo);
        return Response.ok().data("id",courseId);
    }

    @ApiOperation("课程信息确认")
    @GetMapping("/course/sure/{courseId}")
    public Response sureCourseInfo(@PathVariable("courseId")Integer courseId){
        CourseSureInfoVo courseSureInfoVo = courseService.sureCourseInfo(courseId);
        return Response.ok().data("info",courseSureInfoVo);
    }

    @ApiOperation("/发布课程信息")
    @PutMapping("/course/publish")
    public Response publishCourseInfo(@RequestParam("courseId") Integer courseId){
        courseService.publishCourseInfo(courseId);
        return Response.ok();
    }

    @ApiOperation("条件课程查询")
    @GetMapping("/course/list")
    public Response queryCourseCondition(String keyword,Integer start,Integer limit){
        PageHelper.startPage(start,limit);
        List<CourseSureInfoVo> courseSureInfoVos = courseService.queryCourse(keyword);
        PageInfo<CourseSureInfoVo> pages = new PageInfo<>(courseSureInfoVos);
        return Response.ok().data("items",pages);
    }

    @ApiOperation("删除课程")
    @DeleteMapping("/course/{courseId}")
    public Response deleteCourse(@PathVariable("courseId")Integer courseId){
        List<String> courseIds = courseService.getVideoByCourseId(courseId);
        //批量删除视频
        vodClient.removeVideoBatch(courseIds);
        courseService.deleteCourse(courseId);
        return null;
    }
}
