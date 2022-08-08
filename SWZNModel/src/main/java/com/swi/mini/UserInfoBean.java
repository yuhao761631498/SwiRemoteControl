package com.swi.mini;

import java.io.Serializable;

/**
 * Created by xx on 2016/6/8.
 * <p>没有使用Set/Get方法的原因：生成的对象使用频率小</p>
 */
public class UserInfoBean implements Serializable{
    /**
     * <p>用户的邮箱</p>
     */
    public String email;

    /**
     * <p>用户的图像地址</p>
     */
    public String avatar;

    /**
     * <p>用户的性别：1:男，2：女</p>
     */
    public String sex;

    /**
     * <p>国家</p>
     */
    public String country;

    /**
     * <p>省份</p>
     */
    public String province;

    /**
     * <p>城市</p>
     */
    public String city;

    public String nickname;

    public String collectNum;

    public String productNum;

    public String myProductNum;

    public int is_validate_email;

    public String codeCountry;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(String collectNum) {
        this.collectNum = collectNum;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getMyProductNum() {
        return myProductNum;
    }

    public void setMyProductNum(String myProductNum) {
        this.myProductNum = myProductNum;
    }

    public int getIs_validate_email() {
        return is_validate_email;
    }

    public void setIs_validate_email(int is_validate_email) {
        this.is_validate_email = is_validate_email;
    }

    public String getCodeCountry() {
        return codeCountry;
    }

    public void setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
    }
}
