package org.edu.commonutil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Api("统一返回结果")
@Data
public class Response implements Serializable {

    @ApiModelProperty("返回结果成功与否")
    private boolean success;
    @ApiModelProperty("返回结果状态码")
    private Integer code;
    @ApiModelProperty("返回附加信息说明")
    private String message;
    @ApiModelProperty("返回数据")
    private Map<String,Object> data = new HashMap<>();

    //构造函数私有化
    private Response(){}

    //成功返回结果
    public static Response ok(){
        Response response = new Response();
        response.setSuccess(true);
        response.setCode(Code.SUCCESS.getCode());
        response.setMessage("成功");
        return response;
    }

    //失败返回结果
    public static Response fail(){
        Response response = new Response();
        response.setSuccess(false);
        response.setCode(Code.FAIL.getCode());
        response.setMessage("失败");
        return response;
    }

    public Response success(boolean isSuccess){
        this.setSuccess(isSuccess);
        return this;
    }

    public Response code(Integer code){
        this.setCode(code);
        return this;
    }
    public Response message(String message){
        this.setMessage(message);
        return this;
    }
    public Response data(String key,Object val){
        this.getData().put(key,val);
        return this;
    }

}
