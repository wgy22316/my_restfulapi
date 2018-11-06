package com.my.restfulapi.common.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class RestClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(RestClientUtil.class);
    private static RestTemplate restTemplate;

    /**
     * 注入实现类
     *
     * @param client
     */
    @Autowired
    public void setRestTemplate(RestTemplate client) {
        restTemplate = client;
    }

    /**
     * 无参数或者参数附带在url中
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * json格式的post提交
     *
     * @param obj
     * @param url
     * @return
     */
    public static String postJson(String url, Object obj) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String params;
        if (obj == null) {
            params = "{}";
        } else {
            params = JSON.toJSONString(obj);
        }
        HttpEntity<String> formEntity = new HttpEntity<String>(params, headers);
        //return restTemplate.postForObject(url, formEntity, String.class);
        int httpStatus = 0;
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = restTemplate.postForEntity(url, formEntity, String.class);
            httpStatus = responseEntity.getStatusCodeValue();
            if (httpStatus != 200) {
                logger.error("请求第三方接口失败，url:{}, request:{}, httpStatus:{}", url, params, httpStatus);
                //throw new BusinessException("4000", "第三方接口通信失败状态(" + httpStatus + ")");
            }

            String result = responseEntity.getBody();
            return result;
        } catch (Exception e) {
            if (responseEntity != null) {
                httpStatus = responseEntity.getStatusCodeValue();
            }
            logger.error("请求第三方接口失败，url:{}, request:{}, httpStatus:{}, exception:{}", url, params, httpStatus,
                    e.getMessage());
        }

        return null;
    }

    /**
     * form格式的post提交
     *
     * @param map
     * @param url
     * @return
     */
    public static String postForm(String url, Map<String, String> map) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String> me : map.entrySet()) {
            params.add(me.getKey(), me.getValue());
        }
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }
}
