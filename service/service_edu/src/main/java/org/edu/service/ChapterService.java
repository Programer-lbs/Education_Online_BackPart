package org.edu.service;

import org.edu.bean.course.EduChapter;

public interface ChapterService {
    //添加章节信息,返回章节id
    void saveChapter(EduChapter eduChapter);

    //修改章节信息
    void updateChapter(EduChapter eduChapter);

    //删除章节信息
    void deleteChapter(EduChapter eduChapter);

    //根据chapterID查询章节
    EduChapter getById(Integer chapterId);
}
