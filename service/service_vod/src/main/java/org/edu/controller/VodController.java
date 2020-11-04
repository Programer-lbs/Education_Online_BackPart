package org.edu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.edu.commonutil.Response;
import org.edu.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api("视频模块")
@RestController
@RequestMapping("/video")
public class VodController {

    @Autowired
    private VodService vodService;

    @ApiOperation("上传视频")
    @PostMapping("/upload")
    public Response uploadVideo(MultipartFile file){
        try{
            String fileFullName = file.getOriginalFilename();
            String VideoTitle = fileFullName.substring(0,fileFullName.lastIndexOf("."));
            String videoId = vodService.uploadVideo(file,VideoTitle);
            return Response.ok().data("videoId",videoId).data("fileName",VideoTitle);
        }catch(Exception e){
            return Response.fail().data("error",e.getMessage());
        }
    }

    @ApiOperation("删除视频")
    @DeleteMapping("/remove/{videoId}")
    public Response removeVideo(@PathVariable String videoId){
        if(vodService.removeVideo(videoId)){
            return Response.ok();
        }
        return Response.fail();
    }

    @ApiOperation("批量删除视频")
    @DeleteMapping("/remove_batch")
    public Response removeVideoBatch(@RequestParam("videoId") List<String> videoId){
        if(vodService.removeVideoBatch(videoId)){
            return Response.ok().data("message","删除成功");
        }
        return Response.fail().data("message","删除失败");
    }
}
