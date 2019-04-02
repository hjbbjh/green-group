package com.hjb.utils;

import com.hjb.common.constant.CodeInfo;
import com.hjb.common.exception.GreenGroupException;
import com.hjb.dto.Msg;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ClassName: UserMain
 * Description:
 * Created by hjb on 2019/3/29 9:26
 */
public class HttpClientUtils {

    public static Msg<String> doPostWithJsonBody(String url, String body, String token) {

        Msg<String> responseDto = new Msg<>();

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(body, "UTF-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        httpPost.setHeader("Authorization", token);

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                responseDto.setData(EntityUtils.toString(responseEntity));
            }
            responseDto.setCode(String.valueOf(response.getStatusLine().getStatusCode()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new GreenGroupException(new Msg<>(CodeInfo.HTTP_REQUEST_ERROR));
        }  finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseDto;
    }


    public static Msg<String> doGet(String url, String token,List<NameValuePair> params) {
        Msg<String> responseDto = new Msg<>();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            String reqUrl = url;
            if(null != params && params.size() > 0){
                String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(params, "UTF-8"));
                reqUrl += "?" + paramsStr;
            }
            HttpGet httpget = new HttpGet(reqUrl);
            httpget.setHeader("Authorization", token);
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseDto.setData(EntityUtils.toString(entity));
            }
            responseDto.setCode(String.valueOf(response.getStatusLine().getStatusCode()));
            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseDto;
    }


    public static Msg<String> doPostWithParams(String url, Map<String,String> map) {
        Msg<String> responseDto = new Msg<>();
        CloseableHttpClient client = HttpClients.createDefault();

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;

        try {
            HttpPost httpPost = new HttpPost(url);

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            if(map!=null){
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,  Consts.UTF_8));

            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setHeader("Accept", "application/json");

           response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseDto.setData(EntityUtils.toString(entity,  Consts.UTF_8));
            }
            responseDto.setCode(String.valueOf(response.getStatusLine().getStatusCode()));
            EntityUtils.consume(entity);

        }catch (Exception e){
            e.printStackTrace();
            throw new GreenGroupException(new Msg<>(CodeInfo.HTTP_REQUEST_ERROR));
        }finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return responseDto;
    }
}
