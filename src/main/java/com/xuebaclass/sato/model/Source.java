package com.xuebaclass.sato.model;

/**
 * Created by sunhao on 2017-09-05.
 */
public enum Source {
    //APP弹出框
    APP_POPUP("1"),
    //app入口
    APP_ENTRANCE("2"),
    //官网
    WEBSITE("3"),
    //ec历史数据
    EC("4"),
    //后台添加
    BACKEND("5");

    private String code;

    private Source(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
