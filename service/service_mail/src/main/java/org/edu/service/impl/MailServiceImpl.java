package org.edu.service.impl;

import org.edu.Exception.MailException;
import org.edu.service.MailService;
import org.edu.utild.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private JavaMailSender sender;


    @Override
    public void sendMail(String mail) {
        checkMailFormat(mail);
        String codeExist = redisTemplate.opsForValue().get(mail + ":code");
        if(codeExist!=null){
            throw new MailException("验证码已发送");
        }
        String codeNum = redisTemplate.opsForValue().get(mail + ":code:num");

        if(codeNum!=null){
            if(Integer.parseInt(codeNum)>=3) {
                throw new MailException("您的邮箱今日请求验证码次数已达到三次");
            }else{
                String code = MailUtil.generateCode(6);
                redisTemplate.opsForValue().set(mail+":code",String.valueOf(code),3, TimeUnit.MINUTES);
                redisTemplate.opsForValue().increment(mail + ":code:num");
                sendMail(mail,code);
            }
        }else{
            String code = MailUtil.generateCode(6);
            redisTemplate.opsForValue().set(mail+":code",String.valueOf(code),3, TimeUnit.MINUTES);
            redisTemplate.opsForValue().set(mail+":code:num","1",1,TimeUnit.DAYS);
            sendMail(mail,code);
            }
    }

    @Override
    public void checkMailFormat(String mail) {
        String regex = "\\w+@\\w+\\.(com|163)";
        if(!mail.matches(regex)){
            throw new MailException("邮箱格式不正确");
        }
    }

    @Override
    public void sendMail(String mail,String code) {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setSubject("验证码");
        simpleMessage.setTo(mail);
        simpleMessage.setFrom("1782452111@qq.com");
        simpleMessage.setText("您的验证码为["+code+"]，验证码有效期为3分钟");
        try {
            sender.send(simpleMessage);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new MailException("邮箱不合法");
        }
    }
}
