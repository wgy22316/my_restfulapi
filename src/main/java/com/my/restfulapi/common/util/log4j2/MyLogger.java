package com.my.restfulapi.common.util.log4j2;

import com.my.restfulapi.common.util.EscapeJsonUtil;
import com.my.restfulapi.common.util.ThrowableUtil;
import com.my.restfulapi.common.util.json.JsonUtil;
import com.my.restfulapi.common.util.trace.TraceLogUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public class MyLogger implements Logger {
    private final Logger logger;

    private final static String WRAPPER = "";

    private final Class<?> clazz;

    public MyLogger(Class clazz) {
        this.clazz = clazz;
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public static MyLogger getInstance(Class clazz) {
        return new MyLogger(clazz);
    }

    @Override
    public String getName() {
        return clazz.getName();
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void trace(String msg) {
        logger.trace(WRAPPER + replaceSpecialChar(msg) + WRAPPER);
    }

    @Override
    public void trace(String format, Object arg) {
        logger.trace(WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        logger.trace(WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void trace(String format, Object... arguments) {
        logger.trace(WRAPPER + format(format, arguments) + WRAPPER);
    }

    @Override
    public void trace(String msg, Throwable t) {
        logger.trace(WRAPPER + replaceSpecialChar(msg) + " " + replaceSpecialChar(ThrowableUtil.getStackTrace(t,
                true)) + WRAPPER);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return true;
    }

    @Override
    public void trace(Marker marker, String msg) {
        logger.trace(marker, WRAPPER + replaceSpecialChar(msg) + WRAPPER);
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
        logger.trace(marker, WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        logger.trace(marker, WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
        logger.trace(marker, WRAPPER + format(format, argArray) + WRAPPER);
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
        logger.trace(marker,
                WRAPPER + replaceSpecialChar(msg) + " " + replaceSpecialChar(ThrowableUtil.getStackTrace(t, true)) + WRAPPER);
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public void debug(String msg) {
        logger.debug(WRAPPER + replaceSpecialChar(msg) + WRAPPER);
    }

    @Override
    public void debug(String format, Object arg) {
        logger.debug(WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        logger.debug(WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void debug(String format, Object... arguments) {
        logger.debug(WRAPPER + format(format, arguments) + WRAPPER);
    }

    @Override
    public void debug(String msg, Throwable t) {
        logger.debug(WRAPPER + replaceSpecialChar(msg) + " " + replaceSpecialChar(ThrowableUtil.getStackTrace(t,
                true)) + WRAPPER);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return true;
    }

    public void debug(LogData logData) {
        logger.debug(formatLogData(logData));
    }

    @Override
    public void debug(Marker marker, String msg) {
        logger.debug(marker, WRAPPER + replaceSpecialChar(msg) + WRAPPER);
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
        logger.debug(marker, WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        logger.debug(marker, WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void debug(Marker marker, String format, Object... argArray) {
        logger.debug(marker, WRAPPER + format(format, argArray) + WRAPPER);
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
        logger.debug(marker,
                WRAPPER + replaceSpecialChar(msg) + " " + replaceSpecialChar(ThrowableUtil.getStackTrace(t, true)) + WRAPPER);
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public void info(String msg) {
        logger.info(WRAPPER + replaceSpecialChar(msg) + WRAPPER);
    }

    @Override
    public void info(String format, Object arg) {
        logger.info(WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        logger.info(WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void info(String format, Object... arguments) {
        logger.info(WRAPPER + format(format, arguments) + WRAPPER);
    }

    @Override
    public void info(String msg, Throwable t) {
        logger.info(WRAPPER + replaceSpecialChar(msg) + " " + replaceSpecialChar(ThrowableUtil.getStackTrace(t, true)) + WRAPPER);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return true;
    }

    public void info(LogData logData) {
        logger.info(formatLogData(logData));
    }

    @Override
    public void info(Marker marker, String msg) {
        logger.info(marker, WRAPPER + replaceSpecialChar(msg) + WRAPPER);
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
        logger.info(marker, WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
        logger.info(marker, WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void info(Marker marker, String format, Object... argArray) {
        logger.info(marker, WRAPPER + format(format, argArray) + WRAPPER);
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
        logger.info(marker,
                WRAPPER + replaceSpecialChar(msg) + " " + replaceSpecialChar(ThrowableUtil.getStackTrace(t, true)) + WRAPPER);
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public void warn(String msg) {
        logger.warn(WRAPPER + replaceSpecialChar(msg) + WRAPPER);
    }

    @Override
    public void warn(String format, Object arg) {
        logger.warn(WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void warn(String format, Object... arguments) {
        logger.warn(WRAPPER + format(format, arguments) + WRAPPER);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        logger.warn(WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void warn(String msg, Throwable t) {
        logger.warn(WRAPPER + replaceSpecialChar(msg) + " " + replaceSpecialChar(ThrowableUtil.getStackTrace(t, true)) + WRAPPER);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return true;
    }

    public void warn(LogData logData) {
        logger.warn(formatLogData(logData));
    }

    @Override
    public void warn(Marker marker, String msg) {
        logger.warn(marker, WRAPPER + replaceSpecialChar(msg) + WRAPPER);
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
        logger.warn(marker, WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        logger.warn(marker, WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void warn(Marker marker, String format, Object... argArray) {
        logger.warn(marker, WRAPPER + format(format, argArray) + WRAPPER);
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
        logger.warn(marker,
                WRAPPER + replaceSpecialChar(msg) + " " + replaceSpecialChar(ThrowableUtil.getStackTrace(t, true)) + WRAPPER);
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public void error(String msg) {
        logger.error(WRAPPER + replaceSpecialChar(msg) + WRAPPER);
    }

    @Override
    public void error(String format, Object arg) {
        logger.error(WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        logger.error(WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void error(String format, Object... arguments) {
        logger.error(WRAPPER + format(format, arguments) + WRAPPER);
    }

    @Override
    public void error(String msg, Throwable t) {
        logger.error(WRAPPER + replaceSpecialChar(msg) + " " + replaceSpecialChar(ThrowableUtil.getStackTrace(t,
                true)) + WRAPPER);
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return true;
    }

    public void error(LogData logData) {
        logger.error(formatLogData(logData));
    }

    @Override
    public void error(Marker marker, String msg) {
        logger.error(marker, WRAPPER + replaceSpecialChar(msg) + WRAPPER);
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
        logger.error(marker, WRAPPER + format(format, arg) + WRAPPER);
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
        logger.error(marker, WRAPPER + format(format, arg1, arg2) + WRAPPER);
    }

    @Override
    public void error(Marker marker, String format, Object... argArray) {
        logger.error(marker, WRAPPER + format(format, argArray) + WRAPPER);
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
        logger.error(marker,
                WRAPPER + replaceSpecialChar(msg) + " " + replaceSpecialChar(ThrowableUtil.getStackTrace(t, true)) + WRAPPER);
    }

    public String formatLogData(LogData data) {
        if (data == null) {
            return "";
        }

        data.setTraceId(StringUtils.defaultString(TraceLogUtil.get(), "---"));
        return EscapeJsonUtil.escapeJson(JsonUtil.toJson4Log(data));
    }

    private String replaceSpecialChar(String src) {
        if (StringUtils.isBlank(src)) {
            return "";
        }
        String traceId = TraceLogUtil.get();
        if (StringUtils.isBlank(traceId)) {
            return EscapeJsonUtil.escapeJson(src);
        }
        return "TraceID[" + traceId + "]," + EscapeJsonUtil.escapeJson(src);
    }

    private String format(String template, Object... values) {
        template = StringUtils.replace(template, "{}", "%s");
        return replaceSpecialChar(String.format(template, values));
    }
}
