package org.edu.controller;

import org.edu.commonutil.Response;
import org.edu.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("/mail/{receiver}")
    public Response sendMail(@PathVariable("receiver")String receiver){
        mailService.sendMail(receiver);
        return Response.ok().data("message","验证码发送成功");
    }
}
