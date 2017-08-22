package com.xuebaclass.sato.model.request;

import java.util.List;
import java.util.Map;

/**
 * Created by sunhao on 2017-08-21.
 */
public class StartProcessInstanceRequest {

    private String processDefinitionKey;
    private String businessKey;
    private List<Map<String, String>> variables;

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public List<Map<String, String>> getVariables() {
        return variables;
    }

    public void setVariables(List<Map<String, String>> variables) {
        this.variables = variables;
    }
}
