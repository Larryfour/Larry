package com.xuebaclass.sato.model.request;

/**
 * Created by sunhao on 2017-08-21.
 */
public class TaskActionsRequest {

    private String action;
    private String assignee;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
}
