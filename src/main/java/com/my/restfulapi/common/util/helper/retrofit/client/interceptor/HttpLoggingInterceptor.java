package com.my.restfulapi.common.util.helper.retrofit.client.interceptor;

import com.google.common.base.Charsets;
import com.my.restfulapi.common.util.ThrowableUtil;
import com.my.restfulapi.common.util.log4j2.MyLogger;
import lombok.NoArgsConstructor;
import okhttp3.*;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor
public class HttpLoggingInterceptor implements Interceptor {

    //private static final Logger logger = LoggerFactory.getLogger(HttpLoggingInterceptor.class);
    private static final Logger logger = MyLogger.getInstance(HttpLoggingInterceptor.class);

    //private final String projName;
//    @Setter
//    private StatsDClient statsDClient;

//    public HttpLoggingInterceptor(String projName) {
//        this.projName = projName;
//    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = null;

        long startNs = System.nanoTime();
        long tookMs;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
            //MwMDCUtil.put(MwMDCUtil.MDC_THIRD_ELAPSED_TIME, String.valueOf(tookMs));
            logger.warn("请求路径 ==> [{}], 请求参数 ==> [{}], 请求异常 ===> [{}], 耗时[{}]ms",
                    buildReqUrl(request), buildReqStr(request), ThrowableUtil.getStackTrace(e), tookMs);
            throw e;
        } finally {
            try {
                tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
                //MwMDCUtil.put(MwMDCUtil.MDC_THIRD_ELAPSED_TIME, String.valueOf(tookMs));
                doFinally(request, response, tookMs);
            } finally {
                //MwMDCUtil.remove(MwMDCUtil.MDC_THIRD_ELAPSED_TIME);
            }
        }
        return response;
    }

    private void doFinally(Request request, Response response, long tookMs) throws IOException {
        String reqUrl = buildReqUrl(request);
        //String prefix = buildMetricsPrefixName(reqUrl);

        String reqStr = buildReqStr(request);
        String respStr = "";
        if (Objects.nonNull(response)) {
            ResponseBody responseBody = response.body();
            if (Objects.nonNull(responseBody) && HttpHeaders.hasBody(response) && response.isSuccessful()) {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE);
                Buffer buffer = source.getBuffer();

                Charset charset;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(Charsets.UTF_8);
                    respStr = buffer.clone().readString(charset);
                }

                logger.info("请求路径 ==> [{}], 请求参数 ==> [{}], 返回信息 ===> [{}], 耗时[{}]ms", reqUrl, reqStr, respStr,
                        tookMs);
                //statIncrement(prefix + RestMetricsConst.REQ_EXE_SUC);
            } else {
                logger.warn("请求路径 ==> [{}], 请求参数 ==> [{}], 错误码[{}], 返回信息 ===> [{}], 耗时[{}]ms", reqUrl, reqStr,
                        response.code(), response.message(), tookMs);
                //statIncrement(prefix + RestMetricsConst.REQ_EXE_FAIL);
            }
        }

        //statRecord(prefix + RestMetricsConst.REQ_EXE_TIME, tookMs);
    }

    private String buildReqUrl(Request request) {
        return request.url().toString();
    }

//    private String buildMetricsPrefixName(String url) {
//        String urlMethod = MoreUrlUtil.getUrlSuffix(url);
//        return this.projName + "." + urlMethod;
//    }

    private String buildReqStr(Request request) throws IOException {
        String reqStr = "";
        RequestBody requestBody = request.body();
        if (Objects.nonNull(requestBody)) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(Charsets.UTF_8);
                reqStr = buffer.readString(charset);
            }
        }
        return reqStr;
    }

    private void statIncrement(String metric) {
//        if (Objects.nonNull(statsDClient)) {
//            statsDClient.increment(metric);
//        }
    }

    private void statRecord(String metric, long execTime) {
//        if (Objects.nonNull(statsDClient)) {
//            statsDClient.recordExecutionTime(metric, execTime);
//        }
    }
}
