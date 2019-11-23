package com.my.restfulapi.common.util.helper.retrofit;

import com.my.restfulapi.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Component
@AllArgsConstructor
public class RetrofitHelper {

    private static final Logger logger = LoggerFactory.getLogger(RetrofitHelper.class);

    private static final RetryTemplate retryTemplate;

    static {
        retryTemplate = new RetryTemplate();
    }

    public <T> T execute(Call<T> call) {
        return execute(call, true);
    }

    public <T> T execute(Call<T> call, boolean retry) {
        try {
            Response<T> response = retry ? retryTemplate.execute(context -> call.clone().execute()) : call.execute();
            return extract(response);
        } catch (Exception e) {
            logger.warn("处理异常 ===> ", e);
        }
        return null;
    }

    public <T> T extract(Response<T> response) {
        if (response.isSuccessful()) {
            return response.body();
        }
        //throw new CommunicationException(response.message());
        throw new BusinessException("50000", response.message());
    }

    public <T> void enqueue(Call<T> call, Callback<T> callback) {
        call.enqueue(callback);
    }
}
