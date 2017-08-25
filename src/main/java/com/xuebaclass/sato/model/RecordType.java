package com.xuebaclass.sato.model;

/**
 * Created by sunhao on 2017-08-24.
 */
public enum RecordType {

    SYSTEM("1"), ARTIFICIAL("2");

    private String code;

    private RecordType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
