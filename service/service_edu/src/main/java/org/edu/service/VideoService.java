package org.edu.service;

import org.edu.bean.course.EduVideo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideoService {

    //添加小节信息
    void addVideo(EduVideo video);

    //修改小节信息
    void updateVideo(EduVideo video);

    //删除小节信息
    void deleteVideo(Integer videoId);

    //获取小节信息
    EduVideo getById(Integer videoId);
}
