package com.my.restfulapi.third.feign.client;

import com.my.restfulapi.dto.request.UserInfoRequest;
import com.my.restfulapi.dto.response.DataResult;
import feign.Headers;
import feign.RequestLine;

public interface UserFeignClient {

    @RequestLine("GET user/ping")
    DataResult ping();

    @Headers({"Content-Type: application/json"})
    @RequestLine("POST user/getUserInfoById")
    DataResult getUserInfoById(UserInfoRequest userInfoRequest);

    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    @RequestLine("POST user/delUserById")
    DataResult delUserById(Integer id);
}
