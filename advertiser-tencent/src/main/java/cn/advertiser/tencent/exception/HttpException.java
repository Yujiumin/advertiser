package cn.advertiser.tencent.exception;

import okhttp3.Response;

/**
 * @author Michael
 * @date 2024/2/28
 */
public class HttpException extends RuntimeException {

    private Response response;

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpException(String message, Response response) {
        super(message);
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
