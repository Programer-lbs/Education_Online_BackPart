package org.edu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.edu.bean.course.EduVideo;
import org.edu.bean.vo.ChapterVideoRespVo;
import org.edu.commonutil.Response;
import org.edu.service.CourseService;
import org.edu.service.VideoService;
import org.edu.service.VodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("小节信息模块")
@RestController
@RequestMapping("/service_edu")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private VodClient vodClient;

    @ApiOperation("添加小节信息")
    @PostMapping("/video")
    public Response addVideo(@RequestBody EduVideo eduVideo){
        videoService.addVideo(eduVideo);
        List<ChapterVideoRespVo> chapterVideo = courseService.listAllChapterVideo(eduVideo.getCourseId());
        return Response.ok().data("items",chapterVideo);
    }


    @ApiOperation("修改小节信息")
    @PutMapping("/video")
    public Response updateVideo(@RequestBody EduVideo eduVideo){
        videoService.updateVideo(eduVideo);
        List<ChapterVideoRespVo> chapterVideo = courseService.listAllChapterVideo(eduVideo.getCourseId());
        return Response.ok().data("items",chapterVideo);
    }

    //TODO
    @ApiOperation("删除小节信息")
    @DeleteMapping("/video/{courseId}/{videoId}")
    public Response updateVideo(@PathVariable("courseId")Integer courseId,@PathVariable("videoId") Integer videoId){
        //先获取要删除视频的信息，获得其要删除视频的ID
        EduVideo video = videoService.getById(videoId);
        //删除云端视频
        vodClient.removeVideo(video.getVideoSourceId());
        //删除小节信息
        videoService.deleteVideo(videoId);
        List<ChapterVideoRespVo> chapterVideo = courseService.listAllChapterVideo(courseId);
        return Response.ok().data("items",chapterVideo);
    }
    @DeleteMapping("/video/{videoId}")
    public Response testFeign(@PathVariable("videoId") String videoId){
        vodClient.removeVideo(videoId);
        return Response.ok();
    }
}
