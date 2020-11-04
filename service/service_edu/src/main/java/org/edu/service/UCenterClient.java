package org.edu.service;

import org.edu.commonutil.Response;
import org.edu.service.impl.UCenterClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "SERVICE-UCENTER",fallback = UCenterClientImpl.class)
@Component
public interface UCenterClient {

    @GetMapping("/service_ucenter/members")
    public Response getMemInfo(@SpringQueryMap Long memberId);
}
