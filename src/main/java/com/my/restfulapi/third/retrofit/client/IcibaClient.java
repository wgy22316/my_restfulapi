package com.my.restfulapi.third.retrofit.client;

import com.my.restfulapi.third.retrofit.response.TranslationResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IcibaClient {

    /**
     * 获取爱词霸内容
     *
     * @return
     */
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<TranslationResponse> get();
}
