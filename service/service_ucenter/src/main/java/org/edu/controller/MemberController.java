package org.edu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.edu.bean.UcenterMember;
import org.edu.bean.vo.LoginVo;
import org.edu.bean.vo.RegisterVo;
import org.edu.commonutil.Response;
import org.edu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;


@Api("用户模块")
@RestController
@RequestMapping("/service_ucenter")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Response login(@RequestBody LoginVo loginVo){
        String token = memberService.loginCheck(loginVo);
        return Response.ok().data("token",token);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Response register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return Response.ok().data("message","注册成功");
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/members")
    public Response getMemInfo(@SpringQueryMap Long memberId){
        System.out.println(memberId);
        //String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember memberInfo = memberService.getMemberInfo(memberId);
        System.out.println(memberInfo);
        return Response.ok().data("memberInfo",memberInfo);
    }

    @ApiOperation("获取一天内用户的注册数量")
    @GetMapping("/member/register_num/{day}")
    public Response getRegisterNum(@PathVariable("day") String date){
        Integer registerNumOneDay = memberService.getRegisterNumOneDay(date);
        return Response.ok().data("register_num",registerNumOneDay);
    }
}
