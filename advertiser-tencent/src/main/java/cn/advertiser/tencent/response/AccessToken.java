package cn.advertiser.tencent.response;

import cn.advertiser.tencent.AccountRoleType;
import cn.advertiser.tencent.AccountType;
import cn.advertiser.tencent.RoleType;
import cn.advertiser.tencent.response.base.TencentResponse;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

/**
 * @author Michael
 * @date 2024/2/28
 */
public class AccessToken extends TypeReference<TencentResponse<AccessToken>> {

    @JsonAlias("authorizer_info")
    private AuthorizerInfo authorizerInfo;

    @JsonAlias("access_token")
    private String accessToken;

    @JsonAlias("refresh_token")
    private String refreshToken;

    @JsonAlias("access_token_expire_in")
    private Integer accessTokenExpiresIn;

    @JsonAlias("refresh_token_expire_in")
    private Integer refreshTokenExpiresIn;

    public AuthorizerInfo getAuthorizerInfo() {
        return authorizerInfo;
    }

    public void setAuthorizerInfo(AuthorizerInfo authorizerInfo) {
        this.authorizerInfo = authorizerInfo;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getAccessTokenExpiresIn() {
        return accessTokenExpiresIn;
    }

    public void setAccessTokenExpiresIn(Integer accessTokenExpiresIn) {
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }

    public Integer getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }

    public void setRefreshTokenExpiresIn(Integer refreshTokenExpiresIn) {
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    public static class AuthorizerInfo {

        @JsonAlias("account_uin")
        private Integer accountUin;

        @JsonAlias("account_id")
        private Integer accountId;

        @JsonAlias("scope_list")
        private List<String> scopeList;

        @JsonAlias("wechat_account_id")
        private String wechatAccountId;

        @JsonAlias("account_role_type")
        private AccountRoleType accountRoleType;

        @JsonAlias("account_type")
        private AccountType accountType;

        @JsonAlias("role_type")
        private RoleType roleType;

        public Integer getAccountUin() {
            return accountUin;
        }

        public void setAccountUin(Integer accountUin) {
            this.accountUin = accountUin;
        }

        public Integer getAccountId() {
            return accountId;
        }

        public void setAccountId(Integer accountId) {
            this.accountId = accountId;
        }

        public List<String> getScopeList() {
            return scopeList;
        }

        public void setScopeList(List<String> scopeList) {
            this.scopeList = scopeList;
        }

        public String getWechatAccountId() {
            return wechatAccountId;
        }

        public void setWechatAccountId(String wechatAccountId) {
            this.wechatAccountId = wechatAccountId;
        }

        public AccountRoleType getAccountRoleType() {
            return accountRoleType;
        }

        public void setAccountRoleType(AccountRoleType accountRoleType) {
            this.accountRoleType = accountRoleType;
        }

        public AccountType getAccountType() {
            return accountType;
        }

        public void setAccountType(AccountType accountType) {
            this.accountType = accountType;
        }

        public RoleType getRoleType() {
            return roleType;
        }

        public void setRoleType(RoleType roleType) {
            this.roleType = roleType;
        }
    }

}
