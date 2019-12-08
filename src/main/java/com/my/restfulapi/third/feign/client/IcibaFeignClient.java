package com.my.restfulapi.third.feign.client;

import com.my.restfulapi.third.retrofit.response.TranslationResponse;
import feign.RequestLine;

public interface IcibaFeignClient {

    /**
     * 获取爱词霸内容
     *
     * @return
     */
    @RequestLine("GET ajax.php?a=fy&f=auto&t=auto&w=hello%20world)")
    TranslationResponse get();
}
