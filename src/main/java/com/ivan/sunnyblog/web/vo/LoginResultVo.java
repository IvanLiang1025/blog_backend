package com.ivan.sunnyblog.web.vo;

/**
 * Author: jinghaoliang
 **/

public class LoginResultVo {
    String accessToken;
    Short role;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Short getRole() {
        return role;
    }

    public void setRole(Short role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "LoginResultVo{" +
                "accessToken='" + accessToken + '\'' +
                ", role=" + role +
                '}';
    }
}
