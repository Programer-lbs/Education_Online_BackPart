package org.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.edu.bean.course.EduChapter;
import org.edu.bean.course.EduCourse;
import org.edu.bean.course.EduCourseDescription;
import org.edu.bean.course.EduVideo;
import org.edu.bean.vo.ChapterVideoRespVo;
import org.edu.bean.vo.CourseInfoVo;
import org.edu.bean.vo.CourseSureInfoVo;
import org.edu.commonutil.OssTemplate;
import org.edu.exception.CourseException;
import org.edu.mapper.ChapterMapper;
import org.edu.mapper.CourseDescriptionMapper;
import org.edu.mapper.CourseMapper;
import org.edu.mapper.VideoMapper;
import org.edu.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional(rollbackFor = Exception.class)  //开启事务
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private OssTemplate ossTemplate;
    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public Integer saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        System.out.println(eduCourse);
        int i = courseMapper.insert(eduCourse);
        if(i==0){
            throw new CourseException("添加课程信息失败");
        }
        log.info("message:",eduCourse);
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        int j = courseDescriptionMapper.insert(eduCourseDescription);
        if(j==0){
            throw new CourseException("添加课程简介失败");
        }
        //返回课程信息的ID
        return eduCourse.getId();
    }

    @Override
    public String coverUrl(MultipartFile file) throws IOException {
        String fileUrl  = ossTemplate.upload(file.getOriginalFilename(), file.getInputStream());
        return fileUrl;
    }


    @Override
    public List<ChapterVideoRespVo> listAllChapterVideo(Integer courseId) {
        //查询所有章节
        QueryWrapper<EduChapter> eduChapterQueryWrapper = new QueryWrapper<>();
        eduChapterQueryWrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = chapterMapper.selectList(eduChapterQueryWrapper);

        //查询所有小节
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",courseId);
        List<EduVideo> eduVideos = videoMapper.selectList(eduVideoQueryWrapper);

        //封装
        List<ChapterVideoRespVo> chapterVideoRespVos  = new ArrayList<>();
        for(EduChapter eduChapter:eduChapters){
            ChapterVideoRespVo vo  = new ChapterVideoRespVo();
            vo.setChapter(eduChapter);
            for(EduVideo eduVideo:eduVideos){
                if(eduVideo.getChapterId()==eduChapter.getId()){
                    vo.getEduVideoList().add(eduVideo);
                }
            }
            chapterVideoRespVos.add(vo);
        }
        return chapterVideoRespVos;
    }

    @Override
    public CourseInfoVo getCourseInfo(Integer courseId) {
        //获取course表信息
        EduCourse eduCourse = courseMapper.selectById(courseId);

        //获取description表信息
        EduCourseDescription eduCourseDescription = courseDescriptionMapper.selectById(courseId);

        //信息封装
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        courseInfoVo.setDescription(eduCourseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public Integer updateCourse(CourseInfoVo courseInfoVo) {
        //修改course表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int affect = courseMapper.updateById(eduCourse);
        if(affect==0){
            throw new CourseException("修改失败");
        }

        //修改description表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        int affect1 = courseDescriptionMapper.updateById(eduCourseDescription);
        if(affect1==0){
            throw new CourseException("修改失败");
        }
        return courseInfoVo.getId();
    }

    @Override
    public CourseSureInfoVo sureCourseInfo(Integer courseId) {
        CourseSureInfoVo courseSureInfoVo = courseMapper.sureCourseInfo(courseId);
        return courseSureInfoVo;
    }

    @Override
    public void publishCourseInfo(Integer courseId) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        int affect = courseMapper.updateById(eduCourse);
        if(affect!=1){
            throw new CourseException("课程发布失败");
        }
    }

    @Override
    public List<CourseSureInfoVo> queryCourse(String keyword) {
        if(StringUtils.isEmpty(keyword)){
            keyword="";
        }
        List<CourseSureInfoVo> courseSureInfoVos = courseMapper.queryCourse("%" + keyword + "%");
        return courseSureInfoVos;
    }

    @Override
    public void deleteCourse(Integer courseId) {
        //删除小节
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",courseId);
        videoMapper.delete(eduVideoQueryWrapper);
        //删除章节
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);
        chapterMapper.delete(chapterQueryWrapper);
        //删除课程简介
        courseDescriptionMapper.deleteById(courseId);
        //删除课程
        courseMapper.deleteById(courseId);
    }

    @Override
    public List<String> getVideoByCourseId(Integer courseId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);

        //只获取列为video_source_id的值
        queryWrapper.select("video_source_id");

        List<EduVideo> eduVideos = videoMapper.selectList(queryWrapper);
        List<String> videoIds = new ArrayList<>();
        for(EduVideo video:eduVideos){
            videoIds.add(video.getVideoSourceId());
        }
        return videoIds;
    }
}
