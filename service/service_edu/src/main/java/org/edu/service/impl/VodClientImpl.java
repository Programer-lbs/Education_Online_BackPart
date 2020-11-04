package org.edu.service.impl;

import org.edu.commonutil.Response;
import org.edu.service.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodClientImpl implements VodClient {
    @Override
    public Response removeVideo(String videoId) {
        return Response.fail().data("msg","服务不可用");
    }

    @Override
    public Response removeVideoBatch(List<String> videoId) {
        return Response.fail().data("msg","服务不可用");
    }
}
