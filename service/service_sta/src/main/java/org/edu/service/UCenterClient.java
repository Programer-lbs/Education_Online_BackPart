package org.edu.service;

import org.edu.commonutil.Response;
import org.edu.service.impl.UCenterClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SERVICE-UCENTER",fallback = UCenterClientImpl.class)
@Component
public interface UCenterClient {
    @GetMapping("/service_ucenter/member/register_num/{day}")
    public Response getRegisterNum(@PathVariable("day") String date);
}
