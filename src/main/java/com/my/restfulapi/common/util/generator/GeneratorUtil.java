package com.my.restfulapi.common.util.generator;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class GeneratorUtil {

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid2() {
        return StringUtils.replaceChars(UUID.randomUUID().toString(), "-", "");
    }
}
