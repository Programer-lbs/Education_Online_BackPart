package org.edu.controller;


import org.edu.commonutil.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class LoginController {

    @PostMapping("/login")
    public Response login(){
        return Response.ok().data("token","admin");
    }

    @GetMapping("/info")
    public Response info(){
        return Response.ok().data("name","admin").data("avatar","nopic");
    }

}

