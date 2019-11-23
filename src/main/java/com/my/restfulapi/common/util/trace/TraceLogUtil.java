package com.my.restfulapi.common.util.trace;

import com.my.restfulapi.common.util.date.Jdk8DateUtil;
import com.my.restfulapi.common.util.generator.GeneratorUtil;

import java.util.HashMap;
import java.util.Map;

public class TraceLogUtil {

    private static final ThreadLocal<Map<String, String>> inheritableThreadLocal = new InheritableThreadLocal<>();

    private static final String TRACE_ID = "Extend-X-B3-TraceId";

    public static void put(String val) {
        put(TRACE_ID, val);
    }

    public static void put(String key, String val) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        Map<String, String> map = inheritableThreadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            inheritableThreadLocal.set(map);
        }
        map.put(key, val);
    }

    public static String get() {
        return get(TRACE_ID);
    }

    public static String get(String key) {
        Map<String, String> map = inheritableThreadLocal.get();
        if ((map != null) && (key != null)) {
            return map.get(key);
        }
        return null;
    }

    public static void remove() {
        remove(TRACE_ID);
    }

    public static void remove(String key) {
        Map<String, String> map = inheritableThreadLocal.get();
        if (map != null) {
            map.remove(key);
        }
    }

    public static void clearTrace() {
        Map<String, String> map = inheritableThreadLocal.get();
        if (map != null) {
            map.clear();
            inheritableThreadLocal.remove();
        }
    }

    public static String generateDefaultRandomSed() {
        return GeneratorUtil.uuid2();
    }

    public static String generateRandomSed(String str) {
        return str + "_" + generateDefaultRandomSed();
    }

    public static String generateDefaultTraceLogIdPrefix() {
        return Jdk8DateUtil.getNowDate2String("yyyyMMdd_HHmmssSSS");
    }

    public static String generateTraceLogIdPrefix(String appId) {
        return appId + "_" + Jdk8DateUtil.getNowDate2String("yyyyMMdd_HHmmssSSS");
    }
}
