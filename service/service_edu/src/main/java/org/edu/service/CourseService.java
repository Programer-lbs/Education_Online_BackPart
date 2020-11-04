package org.edu.service;


import org.edu.bean.vo.ChapterVideoRespVo;
import org.edu.bean.vo.CourseInfoVo;
import org.edu.bean.vo.CourseSureInfoVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CourseService {

    Integer saveCourseInfo(CourseInfoVo courseInfoVo);

    //上传课程封面图片
    String coverUrl(MultipartFile file) throws IOException;

    //根据课程ID查询所有章节和小节
    List<ChapterVideoRespVo> listAllChapterVideo(Integer courseId);

    //根据课程ID获取课程信息
    CourseInfoVo getCourseInfo(Integer courseId);

    //更新课程信息
    Integer updateCourse(CourseInfoVo courseInfoVo);

    //课程信息确认
    CourseSureInfoVo sureCourseInfo(Integer courseId);

    //课程信息发布,将course状态draft更改为Normal
    void publishCourseInfo(Integer courseId);

    //按关键词查询课程列表
    List<CourseSureInfoVo> queryCourse(String keyword);

    //删除课程
    void deleteCourse(Integer courseId);

    //获取课程所有的视频ID
    List<String> getVideoByCourseId(Integer courseId);
}
