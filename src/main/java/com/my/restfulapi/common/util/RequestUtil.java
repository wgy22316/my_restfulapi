package com.my.restfulapi.common.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {

    public static JSONObject getJSONParam(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        JSONObject jsonParam = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = request.getInputStream();
            // 获取输入流
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            jsonParam = JSONObject.parseObject(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return jsonParam;
    }

    public static String getBodyString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = request.getInputStream();
            // 获取输入流
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return sb.toString();
    }

    /**
     * 检查请求参数
     *
     * @param request
     * @return
     */
    public static boolean checkParam(JSONObject request) {
        if (StringUtils.isEmpty(request.getString("appId")) || StringUtils.isEmpty(request.getString("requestId")) || StringUtils.isEmpty(request.getString
                ("timestamp")) || StringUtils.isEmpty(request.getString("language")) || StringUtils.isEmpty(request
                .getString("timeZone")) || StringUtils.isEmpty(request.getString("sign"))) {
            return false;
        }
        return true;
    }

    /**
     * 检查请求时间戳
     *
     * @param request
     * @return
     */
    public static boolean checkTimestamp(JSONObject request) {
        int timestamp = request.getIntValue("timestamp");
        long currentTimestamp = (System.currentTimeMillis() / 1000);

        if (Math.abs(currentTimestamp - timestamp) > 300) {
            return false;
        }
        return true;
    }

    /**
     * 检查请求客户端来源
     *
     * @param request
     * @return
     */
    public static String checkAppId(JSONObject request) {
        String appid = request.getString("appId");
        //map 代替
        Map<String, String> appidMap = new HashMap() {
            {
                put("1000", "cxC2HMc0gCm0Wk7qqEOCWS0h1FoH3b1z");
                put("1001", "yzZ07jfvfssmgpESGutG6y8NK8Xap31x");
            }
        };

        //从配置文件中获取
        String appKey = appidMap.get(appid);
        if (StringUtils.isEmpty(appKey)) {
            return "";
        }
        return appKey;
    }
}

