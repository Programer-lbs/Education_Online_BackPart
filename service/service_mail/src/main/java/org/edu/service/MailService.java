package org.edu.service;

public interface MailService {

    //发送验证码
    void sendMail(String mail);

    //验证邮箱的格式
    void checkMailFormat(String mail);

    //发送验证码邮件
    void sendMail(String mail,String code);
}
