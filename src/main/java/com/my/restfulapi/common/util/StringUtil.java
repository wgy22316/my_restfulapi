package com.my.restfulapi.common.util;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public final class StringUtil {
    private static final Pattern p = Pattern.compile("[\t|\r|\n]");
    private static final Pattern p2 = Pattern.compile("[\"|\\\\]");

    public static String getValue(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        return str;
    }

    public static String getValue(Object str) {
        if (str == null) {
            return "";
        }

        if (StringUtils.isBlank(str.toString())) {
            return "";
        }
        return str.toString();
    }

    public static boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }

    public static boolean isBlank(Object str) {
        return StringUtils.isBlank(getValue(str));
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (!isBlank(str)) {
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String replaceQuota(String str) {
        String dest = "";
        if (!isBlank(str)) {
            Matcher m = p2.matcher(str);
            dest = m.replaceAll("'").replace("''", "'");
        }
        return dest;
    }

    public static String replace(String text) {
        return replace(text, "", "\\\"");
    }

    public static String replace(String text, String searchString, String replaceString) {
        return StringUtils.replace(text, searchString, replaceString);
    }

    /**
     * 高性能的Split，针对char的分隔符号，比JDK String自带的高效.
     * <p>
     * from Commons Lange 3.5 StringUtils, 做如下优化:
     * <p>
     * 1. 最后不做数组转换，直接返回List.
     * <p>
     * 2. 可设定List初始大小.
     * <p>
     * 3. preserveAllTokens 取默认值false
     *
     * @return 如果为null返回null, 如果为""返回空数组
     */
    public static List<String> split(final String str, final char separatorChar, int expectParts) {
        if (str == null) {
            return null;
        }

        final int len = str.length();
        if (len == 0) {
            return Collections.EMPTY_LIST;
        }
        final List<String> list = new ArrayList<>(expectParts);
        int i = 0;
        int start = 0;
        boolean match = false;
        while (i < len) {
            if (str.charAt(i) == separatorChar) {
                if (match) {
                    list.add(str.substring(start, i));
                    match = false;
                }
                start = ++i;
                continue;
            }
            match = true;
            i++;
        }
        if (match) {
            list.add(str.substring(start, i));
        }
        return list;
    }

    /**
     * String 有replace(char,char)，但缺少单独replace first/last的
     */
    public static String replaceFirst(String s, char sub, char with) {
        if (s == null) {
            return null;
        }
        int index = s.indexOf(sub);
        if (index == -1) {
            return s;
        }
        char[] str = s.toCharArray();
        str[index] = with;
        return new String(str);
    }

    /**
     * String 有replace(char,char)替换全部char，但缺少单独replace first/last
     */
    public static String replaceLast(String s, char sub, char with) {
        if (s == null) {
            return null;
        }

        int index = s.lastIndexOf(sub);
        if (index == -1) {
            return s;
        }
        char[] str = s.toCharArray();
        str[index] = with;
        return new String(str);
    }

    /**
     * 判断字符串是否以字母开头
     * <p>
     * 如果字符串为Null或空，返回false
     */
    public static boolean startWith(CharSequence s, char c) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        return s.charAt(0) == c;
    }

    /**
     * 判断字符串是否以字母结尾
     * <p>
     * 如果字符串为Null或空，返回false
     */
    public static boolean endWith(CharSequence s, char c) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        return s.charAt(s.length() - 1) == c;
    }
}
