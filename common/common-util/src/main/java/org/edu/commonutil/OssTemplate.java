package org.edu.commonutil;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;

import java.io.InputStream;

@Data
public class OssTemplate {

    private String endPoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucket; //水桶
    private String dir; //存储目录

    public String upload(String filename, InputStream inputStream){
        OSS ossClient = new OSSClientBuilder().build(endPoint,accessKeyId,accessKeySecret);
        ossClient.putObject(bucket,dir+filename,inputStream);
        ossClient.shutdown();
        String filepath = "https://"+bucket+"."+endPoint+"/"+dir+filename;
        return filepath;
    }
}
