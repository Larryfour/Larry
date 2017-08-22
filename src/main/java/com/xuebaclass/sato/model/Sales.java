package com.xuebaclass.sato.model;

/**
 * Created by sunhao on 2017-08-22.
 */
public class Sales extends AbstractPersistable{
    private String name;
    private String userName;
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
