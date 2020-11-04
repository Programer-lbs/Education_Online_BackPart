package org.edu.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VodService {
    //上传视频到阿里云
    String uploadVideo(MultipartFile file, String title) throws IOException;

    //删除视频
    boolean removeVideo(String videoId);

    //批量删除视频
    boolean removeVideoBatch(List<String> videoIds);
}
