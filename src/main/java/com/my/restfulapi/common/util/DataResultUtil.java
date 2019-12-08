package com.my.restfulapi.common.util;

import com.my.restfulapi.common.util.trace.TraceLogUtil;
import com.my.restfulapi.dto.response.DataResult;

public class DataResultUtil {

    public static DataResult success(Object object) {
        DataResult dataResult = new DataResult(true);
        dataResult.setData(object);
        dataResult.setErrorCode("0");
        dataResult.setErrorDesc("success");
        dataResult.setRequestId(TraceLogUtil.get());
        return dataResult;
    }

    public static DataResult success() {
        return success(null);
    }

    public static DataResult error(String code, String msg) {
        DataResult dataResult = new DataResult(true);
        dataResult.setErrorCode(code);
        dataResult.setErrorDesc(msg);
        dataResult.setRequestId(TraceLogUtil.get());
        return dataResult;
    }
}
