package org.edu.service.impl;

import org.apache.commons.lang.StringUtils;
import org.edu.config.VodTemplate;
import org.edu.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {

    @Autowired
    private VodTemplate vodTemplate;

    @Override
    public String uploadVideo(MultipartFile file,String title) throws IOException {
        String fileName = file.getOriginalFilename();
        String videoId = vodTemplate.uploadVideoStream(title, fileName, file.getInputStream());
        return videoId;
    }

    @Override
    public boolean removeVideo(String videoId) {
        boolean isSuccess = vodTemplate.deleteVideo(videoId);
        return isSuccess;
    }

    @Override
    public boolean removeVideoBatch(List<String> videoIds) {
        String videos = StringUtils.join(videoIds, ",");
        boolean isSuccess = vodTemplate.deleteVideo(videos);
        return isSuccess;
    }
}
