package cn.advertiser.tencent.exception;

/**
 * @author Michael
 * @date 2024/2/28
 */
public class TencentException extends RuntimeException {

    public TencentException(String message) {
        super(message);
    }

    public TencentException(String message, Throwable cause) {
        super(message, cause);
    }
}
