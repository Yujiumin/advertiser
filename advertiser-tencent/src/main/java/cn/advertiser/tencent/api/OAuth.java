package cn.advertiser.tencent.api;

import cn.advertiser.tencent.exception.HttpException;
import cn.advertiser.tencent.exception.TencentApiException;
import cn.advertiser.tencent.exception.TencentException;
import cn.advertiser.tencent.http.HttpClient;
import cn.advertiser.tencent.json.JsonUtilPlus;
import cn.advertiser.tencent.response.AccessToken;
import cn.advertiser.tencent.response.base.TencentResponse;
import com.google.common.collect.Maps;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * @author Michael
 * @date 2024/2/27
 */
public class OAuth {

    /**
     * 获取AccessToken
     *
     * @param clientId
     * @param clientSecret
     * @param authorizationCode
     * @param redirectUri
     */
    public static AccessToken accessToken(String clientId, String clientSecret, String authorizationCode, String redirectUri) {
        // 请求参数
        final Map<String, Serializable> requestParams = Maps.newLinkedHashMap();
        requestParams.put("client_id", clientId);
        requestParams.put("client_secret", clientSecret);
        requestParams.put("grant_type", "authorization_code");
        requestParams.put("authorization_code", authorizationCode);
        requestParams.put("redirect_uri", redirectUri);
        return doRequest(requestParams);
    }

    /**
     * 刷新Token
     *
     * @param clientId
     * @param clientSecret
     * @param refreshToken
     */
    public static AccessToken refreshToken(String clientId, String clientSecret, String refreshToken) {
        // 请求参数
        final Map<String, Serializable> requestParams = Maps.newLinkedHashMap();
        requestParams.put("client_id", clientId);
        requestParams.put("client_secret", clientSecret);
        requestParams.put("grant_type", "refresh_token");
        requestParams.put("refresh_token", refreshToken);
        return doRequest(requestParams);
    }

    /**
     * 执行请求逻辑
     *
     * @param requestParams
     * @return
     */
    private static AccessToken doRequest(final Map<String, Serializable> requestParams) {
        final Response response = HttpClient.get(Request.OAUTH.getUrl(), requestParams);

        // 响应消息
        final ResponseBody body = response.body();
        if (Objects.isNull(body)) {
            throw new TencentException("响应消息为空");
        }

        final String bodyString;
        try {
            bodyString = StreamUtils.copyToString(body.byteStream(), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new TencentException("响应消息读取异常", ex);
        }

        final TencentResponse<AccessToken> tencentResponse = JsonUtilPlus.toBean(bodyString, new AccessToken(), false);
        if (Objects.isNull(tencentResponse)) {
            // 响应消息转换失败
            throw new HttpException("响应消息转换失败", response);
        }

        final Integer code = tencentResponse.getCode();
        if (code != 0) {
            final String message = tencentResponse.getMessage();
            final String messageCn = tencentResponse.getMessageCn();
            throw new TencentApiException(code, message, messageCn);
        }

        return tencentResponse.getData();
    }

}
