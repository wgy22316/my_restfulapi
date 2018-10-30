package com.my.restfulapi.common.utils;


import com.my.restfulapi.dto.response.DataResult;

public class DataResultUtil {

    public static DataResult success(Object object) {
        DataResult dataResult = new DataResult();
        dataResult.setCode("0");
        dataResult.setMsg("success");
        dataResult.setData(object);
        return dataResult;
    }

    public static DataResult success() {
        return success(null);
    }

    public static DataResult error(String code, String msg) {
        DataResult dataResult = new DataResult();
        dataResult.setCode(code);
        dataResult.setMsg(msg);
        return dataResult;
    }
}
