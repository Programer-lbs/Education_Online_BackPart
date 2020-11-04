package org.edu.commonutil;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtils{
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public static String doGet(String url, Map<String,String> maps){
        String result = "";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if(maps!=null && !maps.isEmpty()) {
                for (Map.Entry<String, String> entry : maps.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            URI uri = uriBuilder.build();
             httpGet = new HttpGet(uri);
            HttpResponse response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                logger.info("请求成功");
                result = EntityUtils.toString(response.getEntity());
            }else{
                logger.error("请求失败");
            }
        }catch (Exception e){
            logger.error("请求失败");
            logger.error(e.getMessage());
        }finally {
            //释放资源
            if(httpGet!=null){
                httpGet.releaseConnection();
            }
        }
        return result;
    }

    public static String doGet(String url){
       return doGet(url, null);
    }

    public static String doPost(String url, Map<String, String> params) {
        String result = "";
        // 创建httpclient对象
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        try { // 参数键值对
            if (null != params && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>();
                NameValuePair pair = null;
                for (String key : params.keySet()) {
                    pair = new BasicNameValuePair(key, params.get(key));
                    pairs.add(pair);
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
                logger.info("返回数据：>>>" + result);
            } else {
                logger.info("请求失败！，url:" + url);
            }
        } catch (Exception e) {
            logger.error("请求失败");
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            if (null != httpPost) {
                // 释放连接
                httpPost.releaseConnection();
            }
        }
        return result;
    }

}