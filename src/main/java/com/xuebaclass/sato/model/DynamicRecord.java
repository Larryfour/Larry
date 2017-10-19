package com.xuebaclass.sato.model;

/**
 * Created by sunhao on 2017-08-17.
 */
public class DynamicRecord extends AbstractPersistable {
    public enum RecordType {
        //系统
        SYSTEM("1"),
        //人工
        ARTIFICIAL("2");

        private String code;

        private RecordType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    private String customerId;
    private String comment;
    private String type;
    private String name;
    private String userName;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
}
