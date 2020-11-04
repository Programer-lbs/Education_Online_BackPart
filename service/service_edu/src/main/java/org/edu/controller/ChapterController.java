package org.edu.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.edu.bean.course.EduChapter;
import org.edu.bean.vo.ChapterVideoRespVo;
import org.edu.commonutil.Response;
import org.edu.mapper.ChapterMapper;
import org.edu.service.ChapterService;
import org.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("章节模块")
@RestController
@RequestMapping("/course")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;
    @Autowired
    private CourseService courseService;

    @ApiOperation("添加章节信息")
    @PostMapping("/chapter")
    public Response addChapter(@RequestBody EduChapter eduChapter){
        chapterService.saveChapter(eduChapter);
        List<ChapterVideoRespVo> chapterVideoRespVos = courseService.listAllChapterVideo(eduChapter.getCourseId());
        return Response.ok().data("chapterId",chapterVideoRespVos);
    }

    @ApiOperation("修改章节信息")
    @PutMapping("/chapter")
    public Response updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateChapter(eduChapter);
        List<ChapterVideoRespVo> chapterVideoRespVos = courseService.listAllChapterVideo(eduChapter.getCourseId());
        return Response.ok().data("chapterId",chapterVideoRespVos);
    }

   @ApiOperation("删除章节信息")
    @DeleteMapping("/chapter/{chapterId}")
    public Response deleteChapter(@PathVariable("chapterId") Integer chapterId){
       EduChapter eduChapter = chapterService.getById(chapterId);
       chapterService.deleteChapter(eduChapter);
       List<ChapterVideoRespVo> chapterVideoRespVos = courseService.listAllChapterVideo(eduChapter.getCourseId());
       return Response.ok().data("chapterId",chapterVideoRespVos);
   }
}
