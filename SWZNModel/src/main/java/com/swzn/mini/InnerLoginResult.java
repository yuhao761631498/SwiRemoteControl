package com.swzn.mini;

import java.io.Serializable;

/**
 * Created by xx on 2017/11/17.
 */

public class InnerLoginResult implements Serializable {
    private int uid;
    private String email;
    private String access_token;
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
