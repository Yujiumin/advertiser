package cn.advertiser.tencent.http;

import cn.advertiser.tencent.exception.HttpException;
import okhttp3.*;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Yujiumin
 * @date 2022/11/20
 */
public class HttpClient extends OkHttpClient {

    private static OkHttpClient okHttpClient;

    private static final long DEFAULT_READ_TIMEOUT_SECONDS = 30;
    private static final long DEFAULT_WRITE_TIMEOUT_SECONDS = 30;
    private static final long DEFAULT_CONNECT_TIMEOUT_SECONDS = 30;

    static {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    public static Response get(String url, Map<String, ? extends Serializable> params) {
        return get(url, Collections.emptyMap(), params);
    }

    /**
     * GET请求
     *
     * @param url
     * @param headers
     * @param params
     * @return
     */
    public static Response get(String url, Map<String, ? extends Serializable> headers, Map<String, ? extends Serializable> params) {
        final HttpUrl.Builder httpUrlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        if (!CollectionUtils.isEmpty(params)) {
            params.forEach((name, value) -> httpUrlBuilder.addQueryParameter(name, value.toString()));
        }

        final HttpUrl httpUrl = httpUrlBuilder.build();

        final Request.Builder requestBuilder = new Request.Builder();
        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach((name, value) -> requestBuilder.addHeader(name, value.toString()));
        }

        try {
            final Request request = requestBuilder.url(httpUrl).build();
            return okHttpClient.newCall(request).execute();
        } catch (IOException ex) {
            throw new HttpException("请求失败", ex);
        }
    }

    /**
     * POST请求
     *
     * @param url
     * @param headers
     * @param body
     * @return
     */
    public static Response post(String url, Map<String, ? extends Serializable> headers, String body) throws IOException {
        final Request.Builder requestBuilder = new Request.Builder();

        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach((name, value) -> requestBuilder.addHeader(name, value.toString()));
        }

        final Request request = requestBuilder.url(Objects.requireNonNull(HttpUrl.parse(url)))
                .post(RequestBody.create(MediaType.parse("application/json"), body))
                .build();
        return okHttpClient.newCall(request).execute();
    }
}
