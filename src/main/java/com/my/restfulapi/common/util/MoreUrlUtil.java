package com.my.restfulapi.common.util;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor
public class MoreUrlUtil {
    public static String getUrlSuffix(String url) {
        if (StringUtils.isBlank(url)) {
            return StringUtils.EMPTY;
        }

        if (url.endsWith("/")) {
            return getUrlSuffix(url.substring(0, url.length() - 1));
        }

        return StringUtils.substring(url, url.lastIndexOf("/") + 1);
    }
}
