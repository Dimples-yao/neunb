/*
*Created by liulei on 2016/5/14.
*/
package com.liuzg.jswebextra.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by hong on 2016-12-19.
 */
public class HttpRequestUtils {

    /**
     * get访问服务器
     * @param url
     * @return
     * @throws IOException
     */
    public static String sendGet(String url) {
        HttpClient httpClient;
        HttpGet httpGet;
        String result = null;
        httpClient = new DefaultHttpClient();
        httpGet = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                result = EntityUtils.toString(resEntity,"utf-8");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
    /**
     * 发送json字符串到远程服务器
     * 应用有：给微信企业号发送消息
     * create by hong  2016-12-19
     * @param url
     * @param content json字符串内容
     * @return
     * @throws IOException
     */
    public static String sendPostJSON(String url,String content) throws IOException {
        HttpClient httpClient;
        HttpPost httpPost;
        String result = null;
        httpClient = new DefaultHttpClient();
        httpPost = new HttpPost(url);
        StringEntity entity=new StringEntity(content,"utf-8");
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);
        if(response != null){
            HttpEntity resEntity = response.getEntity();
            if(resEntity != null){
                result = EntityUtils.toString(resEntity,"utf-8");
            }
        }
        return result;
    }

    /**
     * 发送post请求
     * create by hong 2016-12-19
     * @param url  网址
     * @param params 参数列表
     * @param charset 编码集
     * @return 响应内容字符串
     */
    public static String  doPost(String url, Map<String ,String > params,String charset) throws IOException {
        HttpClient httpClient;
        HttpPost httpPost;
        String result = null;
        httpClient = new DefaultHttpClient();
        httpPost = new HttpPost(url);
        if(charset==null || charset.equals("") || charset.equals("null"))
            charset="UTF-8";
        //设置参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator iterator = params.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
        }
        if(list.size() > 0){
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
            httpPost.setEntity(entity);
        }
        HttpResponse response = httpClient.execute(httpPost);
        if(response != null){
            HttpEntity resEntity = response.getEntity();
            if(resEntity != null){
                result = EntityUtils.toString(resEntity,charset);
            }
        }
        return result;
    }

}
