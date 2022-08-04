package com.swzn.mini;

import java.io.Serializable;

/**
 * Created by xx on 2017/11/17.
 */

public class LoginResult implements Serializable {
    private int error;
    private InnerLoginResult data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public InnerLoginResult getData() {
        return data;
    }

    public void setData(InnerLoginResult data) {
        this.data = data;
    }

}
