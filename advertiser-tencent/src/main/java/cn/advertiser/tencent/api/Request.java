package cn.advertiser.tencent.api;

/**
 * @author Michael
 * @date 2024/2/28
 */
public enum Request {

    /**
     * 授权
     */
    OAUTH("https://api.e.qq.com/oauth/token");

    final String url;

    Request(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
