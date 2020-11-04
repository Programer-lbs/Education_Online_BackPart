package org.edu.config;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.*;
import lombok.Data;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class VodTemplate {

    private String accessKeyId;
    private String accessKeySecret;

    public  DefaultAcsClient initVodObject(){
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient defaultAcsClient = new DefaultAcsClient(profile);
        return defaultAcsClient;
    }

    //使用播放地址 播放视频 、针对于未加密视频可使用此方法
    public Map<String,Object> getPlayInfoByAddress(String videoId) throws ClientException {
        Map<String,Object> maps = new HashMap<>();

        DefaultAcsClient client = initVodObject();
        //设置请求
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        //设置向应
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        //设置请求视频的ID
        request.setVideoId(videoId);
        //获取返回数据
        response = client.getAcsResponse(request);
        //获取相关信息
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        for (int i = 0;i<playInfoList.size();i++){
            maps.put("videoUrl"+i,playInfoList.get(i).getPlayURL());
            System.out.println("play info url :" +playInfoList.get(i).getPlayURL());
        }
        //base信息 视频标题
        System.out.println("video title :" + response.getVideoBase().getTitle());
        maps.put("videoTitle",response.getVideoBase().getTitle());
        return maps;
    }

    //使用播放凭证播放视频 ，适合用户加密的视频
    public Map<String,Object> getPlayInfoByAuth(String videoId) throws ClientException {
        Map<String,Object> maps = new HashMap<>();

        //初始化对象
        DefaultAcsClient client = initVodObject();
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        request.setVideoId(videoId);
        GetVideoPlayAuthResponse acsResponse = client.getAcsResponse(request);
        maps.put("auth",acsResponse.getPlayAuth());
        System.out.println(acsResponse.getPlayAuth());
        return maps;
    }


    //本地视频文件上传
    public String uploadVideoFile(String title, String fileName) {
        //title 文件名称
        //filename 本地文件路径和名称
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        request.setPartSize(2 * 1024 * 1024L);
        request.setTaskNum(1);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        if (response.isSuccess()) {
            //返回上传视频的ID
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
        return response.getVideoId();
    }

    //通过流上传
    public String uploadVideoStream( String title, String fileName, InputStream inputStream) {
        UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
        return response.getVideoId();
    }

    //删除云端视频
    public boolean deleteVideo(String videoId){
        DefaultAcsClient client = initVodObject();
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoId);
        try {
            DeleteVideoResponse response = client.getAcsResponse(request);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
