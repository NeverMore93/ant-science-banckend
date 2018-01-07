package com.antScience.foundation.common.submail;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by lirui on 2017/12/27.
 */
@Service
public class MessageUtil {

    private static final String UTF8 = "UTF-8";
    private static final String APP_ID = "18631";
    private static final String PROJECT = "hIdfR3";
    private static final String APP_KEY = "17d0979bebbe51d711119d08271d4880";
    private static final String URL = "https://api.mysubmail.com/message/xsend";

    public static void main(String[] args) throws IOException {
        send("15715185099");
    }

    private static String generateCode() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += random.nextInt(10);
        }
        return result;
    }

    public static String send(String phone) throws IOException {
        TreeMap<String, Object> requestData = new TreeMap<>();
        requestData.put("appid", APP_ID);
        requestData.put("project", PROJECT);
        requestData.put("to", phone);
        JSONObject vars = new JSONObject();
        String code = generateCode();
        vars.put("code", code);
        if (!vars.isEmpty()) {
            requestData.put("vars", vars.toString());
        }
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        ContentType contentType = ContentType.TEXT_PLAIN.withCharset(UTF8);
        for (Map.Entry<String, Object> entry : requestData.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                builder.addTextBody(key, String.valueOf(value), contentType);
            }
        }
        builder.addTextBody("signature", APP_KEY, contentType);
        HttpPost httpPost = new HttpPost(URL);
        httpPost.addHeader("charset", UTF8);
        httpPost.setEntity(builder.build());
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
        HttpResponse response = closeableHttpClient.execute(httpPost);
        HttpEntity httpEntity = response.getEntity();
        String jsonStr;
        if (httpEntity != null) {
            jsonStr = EntityUtils.toString(httpEntity, UTF8);
            System.out.println(jsonStr);
            if (jsonStr.contains("success")) {
                return code;
            }
        }
        return null;
    }
}
