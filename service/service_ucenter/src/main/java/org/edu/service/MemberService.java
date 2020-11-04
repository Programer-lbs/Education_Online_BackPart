package org.edu.service;


import org.edu.bean.UcenterMember;
import org.edu.bean.vo.LoginVo;
import org.edu.bean.vo.RegisterVo;

public interface MemberService {

    //登录验证,登录成功返回token
    String loginCheck(LoginVo loginVo);

    //注册
    void register(RegisterVo registerVo);

    //根据id获取用户信息
    UcenterMember getMemberInfo(Long memberId);

    //获取一天内用户的注册数量
    Integer getRegisterNumOneDay(String date);
}
