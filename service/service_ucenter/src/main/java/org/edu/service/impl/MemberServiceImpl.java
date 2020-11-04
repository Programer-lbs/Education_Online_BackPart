package org.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.edu.bean.UcenterMember;
import org.edu.bean.vo.LoginVo;
import org.edu.bean.vo.RegisterVo;
import org.edu.commonutil.JwtUtils;
import org.edu.exception.MemberException;
import org.edu.mapper.MemberMapper;
import org.edu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String loginCheck(LoginVo loginVo) {
        if(StringUtils.isEmpty(loginVo.getMobile()) || StringUtils.isEmpty(loginVo.getPassword())){
            throw new MemberException("用户民和密码不能为空");
        }
        QueryWrapper<UcenterMember> query1 = new QueryWrapper<>();
        query1.eq("mobile",loginVo.getMobile());
        UcenterMember ucenterMember = memberMapper.selectOne(query1);
        if(ucenterMember==null){
            throw new MemberException("用户不存在，请先注册");
        }
        if(ucenterMember.getIsDisabled()==1){
            throw new MemberException("该用户已被禁止登录，解除限制请联系管理员");
        }
        if(!encoder.matches(loginVo.getPassword(),ucenterMember.getPassword())){
            throw new MemberException("密码不正确");
        }
        String jwtToken = JwtUtils.getJwtToken(String.valueOf(ucenterMember.getId()), ucenterMember.getNickname());
        return jwtToken;
    }

    @Override
    public void register(RegisterVo registerVo) {
        if(StringUtils.isEmpty(registerVo.getMobile()) || StringUtils.isEmpty(registerVo.getPassword())
        || StringUtils.isEmpty(registerVo.getNickname()) || StringUtils.isEmpty(registerVo.getCode())){
            throw new MemberException("注册信息不完整");
        }
        String code = redisTemplate.opsForValue().get(registerVo.getMobile()+":code");
        if(code==null){
            throw new MemberException("请先获取验证码进行注册");
        }
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",registerVo.getMobile());
        Integer integer = memberMapper.selectCount(queryWrapper);
        if(integer!=0){
            throw new MemberException("该邮箱已被注册");
        }
        if(!code.equals(registerVo.getCode())){
            throw new MemberException("验证码不正确");
        }
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(registerVo.getMobile());
        ucenterMember.setNickname(registerVo.getNickname());
        ucenterMember.setPassword(encoder.encode(registerVo.getPassword()));
        int affect = memberMapper.insert(ucenterMember);
        if(affect!=1){
            throw new MemberException("注册失败");
        }
    }

    @Override
    public UcenterMember getMemberInfo(Long memberId) {
        UcenterMember ucenterMember = memberMapper.selectById(memberId);
        return ucenterMember;
    }

    @Override
    public Integer getRegisterNumOneDay(String date) {
        return memberMapper.getRegisterNumOneDay(date);
    }
}
