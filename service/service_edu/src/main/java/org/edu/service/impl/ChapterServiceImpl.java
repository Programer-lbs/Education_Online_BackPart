package org.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.edu.bean.course.EduChapter;
import org.edu.bean.course.EduVideo;
import org.edu.exception.CourseException;
import org.edu.mapper.ChapterMapper;
import org.edu.mapper.VideoMapper;
import org.edu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public void saveChapter(EduChapter eduChapter) {
        int affect = chapterMapper.insert(eduChapter);
        if(affect==0){
            throw new CourseException("添加章节信息失败");
        }
    }

    @Override
    public void updateChapter(EduChapter eduChapter) {
        int affect = chapterMapper.updateById(eduChapter);
        if(affect==0){
            throw new CourseException("修改章节信息失败");
        }
    }

    @Override
    public void deleteChapter(EduChapter eduChapter) {
        //删除章节的小节信息
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id",eduChapter.getId());
        queryWrapper.eq("course_id",eduChapter.getCourseId());
        videoMapper.delete(queryWrapper);

        //删除章节信息
        chapterMapper.deleteById(eduChapter.getId());
    }

    @Override
    public EduChapter getById(Integer chapterId) {
        EduChapter eduChapter = chapterMapper.selectById(chapterId);
        return eduChapter;
    }
}
