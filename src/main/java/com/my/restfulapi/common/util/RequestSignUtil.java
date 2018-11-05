package com.my.restfulapi.common.util;

import com.alibaba.fastjson.JSONObject;
import com.my.restfulapi.dto.request.BaseRequest;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RequestSignUtil {

    public static boolean checkSign(JSONObject request, String appKey) {
        Map<String, String> signMap = new TreeMap<>();
        signMap.put("appId", request.getString("appId"));
        signMap.put("requestId", request.getString("requestId"));
        signMap.put("timestamp", request.getString("timestamp"));
        signMap.put("language", request.getString("language"));
        signMap.put("timeZone", request.getString("timeZone"));
        String sign = request.getString("sign");
        String md5Sign = generatorSign((TreeMap) signMap, appKey);
        if (!md5Sign.equals(sign)) {
            return false;
        }
        return true;
    }

    public static String generatorSign(TreeMap map, String appKey) {
        Set<Map.Entry<String, String>> set = map.entrySet();
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> vo : set) {
            sb.append(vo.getKey()).append("&").append(vo.getValue());
        }

        String originStr = sb.append("&").append(appKey).toString();
        String md5SignStr = EncryptUtil.md5Encrypt(originStr);
        return md5SignStr;
    }

    public static void buildBaseRequest(BaseRequest request){

        String appId = "1000";
        String requestId = String.valueOf(System.currentTimeMillis());
        long timestamp = System.currentTimeMillis()/1000;
        String language = "zh-CN";
        String timeZone = "GMT+8";
        String appKey = "cxC2HMc0gCm0Wk7qqEOCWS0h1FoH3b1z";

        request.setAppId(appId);
        request.setRequestId(requestId);
        request.setTimestamp(timestamp);
        request.setLanguage(language);
        request.setTimeZone(timeZone);

        Map<String, String> signMap = new TreeMap<>();
        signMap.put("appId", appId);
        signMap.put("requestId", requestId);
        signMap.put("timestamp", String.valueOf(timestamp));
        signMap.put("language", language);
        signMap.put("timeZone", timeZone);
        String md5Sign = generatorSign((TreeMap) signMap, appKey);
        request.setSign(md5Sign);
    }
}
