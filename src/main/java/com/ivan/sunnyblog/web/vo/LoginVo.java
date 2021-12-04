package com.ivan.sunnyblog.web.vo;

import org.jooq.impl.UpdatableRecordImpl;

import java.io.Serializable;

/**
 * Author: jinghaoliang
 * Date: 2021-09-27 3:10 p.m.
 **/

public class LoginVo implements Serializable {

    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
