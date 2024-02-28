package cn.advertiser.tencent.response.base;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * @author Michael
 * @date 2024/2/28
 */
public class TencentResponse<T> {

    private Integer code;

    private String message;

    @JsonAlias("message_cn")
    private String messageCn;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageCn() {
        return messageCn;
    }

    public void setMessageCn(String messageCn) {
        this.messageCn = messageCn;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
