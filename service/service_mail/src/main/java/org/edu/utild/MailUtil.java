package org.edu.utild;

import java.util.Random;

public class MailUtil {

    //根据num生成随机数
    public static String generateCode(Integer num){
        String code="";
        for(int i = 0;i < num; i++){
            int anInt = new Random().nextInt(10);
            code+=anInt;
        }
        return code;
    }
}
