package org.edu.service;

import io.swagger.annotations.ApiOperation;
import org.edu.commonutil.Response;
import org.edu.service.impl.VodClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "SERVICE-VOD",fallback = VodClientImpl.class)
@Component
public interface VodClient {

    //删除小节视频
    @DeleteMapping("/video/remove/{videoId}")
    public Response removeVideo(@PathVariable("videoId") String videoId);

    //删除整个课程视频
    @DeleteMapping("/video/remove_batch")
    public Response removeVideoBatch(@RequestParam("videoId") List<String> videoId);
}
