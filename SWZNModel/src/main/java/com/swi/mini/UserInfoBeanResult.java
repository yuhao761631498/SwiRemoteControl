package com.swi.mini;


import java.io.Serializable;

/**
 * Created by xx on 2017/11/17.
 */

public class UserInfoBeanResult implements Serializable {
    private int error;
    private InnerUser data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public InnerUser getUserInfoBean() {
        return data;
    }

    public void setUserInfoBean(InnerUser userInfoBean) {
        this.data = userInfoBean;
    }

    public class InnerUser{
        private UserInfoBean user;
        public UserInfoBean getUser() {
            return user;
        }

        public void setUser(UserInfoBean user) {
            this.user = user;
        }

    }
}
