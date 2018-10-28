package com.my.restfulapi.common.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RequestSignUtil {

    public static boolean checkSign(JSONObject request, String appKey) {
        Map<String, String> signMap = new TreeMap<>();
        signMap.put("appid", request.getString("appid"));
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
}
