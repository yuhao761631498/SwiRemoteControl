package com.swzn.mini;

import java.io.Serializable;

/**
 * Created by xx on 2017/11/14.
 */

public class SWZNUser implements Serializable {
    private static final long serialVersionUID = 2104023058352522453L;
    private String email;
    private String phone;
    private String password;
    private String project;
    private String lang;
    private String os;
    private String version;
    private String installation_id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInstallation_id() {
        return installation_id;
    }

    public void setInstallation_id(String installation_id) {
        this.installation_id = installation_id;
    }
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
