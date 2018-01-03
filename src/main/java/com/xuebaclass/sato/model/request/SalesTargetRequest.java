package com.xuebaclass.sato.model.request;

import com.xuebaclass.sato.model.SalesTarget;

import java.util.List;

/**
 * Created by sunhao on 2017-10-18.
 */
public class SalesTargetRequest {

    class CreateSalesTarget extends SalesTarget{
        private String salesName;

        public String getSalesName() {
            return salesName;
        }

        public void setSalesName(String salesName) {
            this.salesName = salesName;
        }
    }

    private String targetMonth;
    private List<SalesTarget> updateTargets;
    private List<CreateSalesTarget> createTargets;
    private List<Integer> deleteTargetIds;

    public String getTargetMonth() {
        return targetMonth;
    }

    public void setTargetMonth(String targetMonth) {
        this.targetMonth = targetMonth;
    }

    public List<SalesTarget> getUpdateTargets() {
        return updateTargets;
    }

    public void setUpdateTargets(List<SalesTarget> updateTargets) {
        this.updateTargets = updateTargets;
    }

    public List<CreateSalesTarget> getCreateTargets() {
        return createTargets;
    }

    public void setCreateTargets(List<CreateSalesTarget> createTargets) {
        this.createTargets = createTargets;
    }

    public List<Integer> getDeleteTargetIds() {
        return deleteTargetIds;
    }

    public void setDeleteTargetIds(List<Integer> deleteTargetIds) {
        this.deleteTargetIds = deleteTargetIds;
    }
}
