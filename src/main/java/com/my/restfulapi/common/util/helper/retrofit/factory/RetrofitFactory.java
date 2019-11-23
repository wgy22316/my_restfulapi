package com.my.restfulapi.common.util.helper.retrofit.factory;

import com.my.restfulapi.common.util.helper.retrofit.client.converter.ToStringConverterFactory;
import com.my.restfulapi.common.util.helper.retrofit.client.interceptor.HttpLoggingInterceptor;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

@Component
@AllArgsConstructor
public class RetrofitFactory {

    private static final OkHttpClient okHttpClient;

    static {
        okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new HttpLoggingInterceptor()).build();
    }

    public <T> T newInstance(String url, Class<T> cls) {
        return newBuilder(toBaseUrl(url)).build().create(cls);
    }

    public Retrofit.Builder newBuilder(String baseUrl) {
        return newBuilder(okHttpClient, baseUrl);
    }

    public Retrofit.Builder newBuilder(OkHttpClient okHttpClient, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addConverterFactory(ToStringConverterFactory.create());
    }

    private String toBaseUrl(String url) {
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException("url不能为空");
        }

        if (!url.endsWith("/")) {
            url = url + "/";
        }
        return url;
    }
}
