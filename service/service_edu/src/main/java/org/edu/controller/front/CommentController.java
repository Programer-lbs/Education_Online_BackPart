package org.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.edu.bean.EduComment;
import org.edu.commonutil.Response;
import org.edu.service.UCenterClient;
import org.edu.service.front.FrontCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api("课程评论模块")
@RestController
@RequestMapping("/service_edu/course")
public class CommentController {
    @Autowired
    private FrontCommentService commentService;

    @Autowired
    private UCenterClient centerClient;

    @ApiOperation("获取课程评论")
    @GetMapping("/comment/{courseId}")
    public Response getCourseComment(@PathVariable("courseId")Integer courseId,
                                     Integer start,Integer limit){
        Page<EduComment> courseComment = commentService.getCourseComment(start, limit, courseId);
        return Response.ok().data("data",courseComment);
    }

    @ApiOperation("添加评论")
    @PostMapping ("/comment")
    public Response addComment(Integer courseId, String content,Long memberId){
        System.out.println(memberId);
        EduComment eduComment = new EduComment();
        centerClient.getMemInfo(memberId);
//        memInfo.getData().get("memberInfo");
//        eduComment.setCourseId(courseId);
//        eduComment.setMemberId(member.getId());
//        eduComment.setNickname(member.getNickname());
//        eduComment.setAvatar(member.getAvatar());
//        eduComment.setContent(content);
//
//        log.info("信息："+eduComment);
//        commentService.saveCourseComment(eduComment);
        return Response.ok();
    }
}
