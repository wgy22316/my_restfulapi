package com.my.restfulapi.third.user;

import com.my.restfulapi.common.utils.EncryptUtil;
import com.my.restfulapi.third.user.request.BaseUserRequest;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class BaseUserAdapter {

    private static final String appId = "1000";
    private static final String appKey = "cxC2HMc0gCm0Wk7qqEOCWS0h1FoH3b1z";
    private final String HOST = "http://127.0.0.1:8090";

    /**
     * 构造请求公共参数
     *
     * @param request
     */
    public static void buildBaseRequest(BaseUserRequest request){

        String requestId = String.valueOf(System.currentTimeMillis());
        long timestamp = System.currentTimeMillis()/1000;
        String language = "zh-CN";
        String timeZone = "GMT+8";

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

    /**
     * 构造签名
     *
     * @param map
     * @param appKey
     * @return
     */
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
