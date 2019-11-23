package com.my.restfulapi.common.util;


import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.EntityArrays;
import org.apache.commons.text.translate.LookupTranslator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EscapeJsonUtil {
    private static final CharSequenceTranslator ESCAPE_JSON;

    static {
        Map<CharSequence, CharSequence> escapeJsonMap = new HashMap<>(16);
        escapeJsonMap.put("\"", "\\\"");
        escapeJsonMap.put("\\", "\\\\");
        escapeJsonMap.put("/", "\\/");
        ESCAPE_JSON = new AggregateTranslator(
                new LookupTranslator(Collections.unmodifiableMap(escapeJsonMap)),
                new LookupTranslator(EntityArrays.JAVA_CTRL_CHARS_ESCAPE)
        );
    }

    public static final String escapeJson(final String input) {
        try {
            return ESCAPE_JSON.translate(input);
        } catch (Exception e) {
            return StringUtil.replaceBlank(input);
        }
    }
}
