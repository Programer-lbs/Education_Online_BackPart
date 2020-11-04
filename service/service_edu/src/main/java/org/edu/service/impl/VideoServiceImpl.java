package org.edu.service.impl;

import org.edu.bean.course.EduVideo;
import org.edu.exception.CourseException;
import org.edu.mapper.VideoMapper;
import org.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public void addVideo(EduVideo video) {
        int affect = videoMapper.insert(video);
        if(affect==0){
            throw new CourseException("添加小节信息失败");
        }
    }

    @Override
    public void updateVideo(EduVideo video) {
        int affect = videoMapper.updateById(video);
        if(affect==0){
            throw new CourseException("修改小节信息失败");
        }
    }

    @Override
    public void deleteVideo(Integer videoId) {
        int affect = videoMapper.deleteById(videoId);
        if(affect==0){
            throw new CourseException("删除小节信息失败");
        }
    }

    @Override
    public EduVideo getById(Integer videoId) {
        EduVideo eduVideo = videoMapper.selectById(videoId);
        return eduVideo;
    }
}
