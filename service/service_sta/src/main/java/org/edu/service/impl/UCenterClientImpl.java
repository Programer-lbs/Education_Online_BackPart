package org.edu.service.impl;

import org.edu.commonutil.Response;
import org.edu.service.UCenterClient;
import org.springframework.stereotype.Component;

@Component
public class UCenterClientImpl  implements UCenterClient {
    @Override
    public Response getRegisterNum(String date) {
        return Response.fail().data("error","服务不可用");
    }
}
