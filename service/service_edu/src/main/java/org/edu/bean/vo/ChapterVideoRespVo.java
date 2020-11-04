package org.edu.bean.vo;

import lombok.Data;
import org.edu.bean.course.EduChapter;
import org.edu.bean.course.EduVideo;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChapterVideoRespVo {
    private EduChapter chapter;
    private List<EduVideo> eduVideoList = new ArrayList<>();
}
