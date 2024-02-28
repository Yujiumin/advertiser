package cn.advertiser.tencent.exception;

/**
 * @author Michael
 * @date 2024/2/28
 */
public class TencentApiException extends TencentException {

    private final Integer code;

    private final String message;

    private final String messageCn;

    public TencentApiException(Integer code, String message, String messageCn) {
        super(message);
        this.code = code;
        this.message = message;
        this.messageCn = messageCn;
    }

    public TencentApiException(Integer code, String message, String messageCn, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
        this.messageCn = messageCn;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getMessageCn() {
        return messageCn;
    }
}
