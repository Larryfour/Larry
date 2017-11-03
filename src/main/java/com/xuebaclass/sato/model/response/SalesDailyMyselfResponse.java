package com.xuebaclass.sato.model.response;

import java.util.List;

/**
 * Created by sunhao on 2017-10-13.
 */
public class SalesDailyMyselfResponse {

    public static class SalesDailyData{
        private String date;
        private Integer distributedNumber;
        private Integer bridgeDuration;
        private Integer offset;
        private Integer rewards;
        private Integer offsetAfter;
        private Integer reservedCount;
        private Integer completedCount;
        private Integer firstOrderCount;
        private Integer firstOrderAmount;
        private Integer repeatOrderCount;
        private Integer repeatOrderAmount;
        private String comment;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getDistributedNumber() {
            return distributedNumber;
        }

        public void setDistributedNumber(Integer distributedNumber) {
            this.distributedNumber = distributedNumber;
        }

        public Integer getBridgeDuration() {
            return bridgeDuration;
        }

        public void setBridgeDuration(Integer bridgeDuration) {
            this.bridgeDuration = bridgeDuration;
        }

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        public Integer getRewards() {
            return rewards;
        }

        public void setRewards(Integer rewards) {
            this.rewards = rewards;
        }

        public Integer getOffsetAfter() {
            return offsetAfter;
        }

        public void setOffsetAfter(Integer offsetAfter) {
            this.offsetAfter = offsetAfter;
        }

        public Integer getReservedCount() {
            return reservedCount;
        }

        public void setReservedCount(Integer reservedCount) {
            this.reservedCount = reservedCount;
        }

        public Integer getCompletedCount() {
            return completedCount;
        }

        public void setCompletedCount(Integer completedCount) {
            this.completedCount = completedCount;
        }

        public Integer getFirstOrderCount() {
            return firstOrderCount;
        }

        public void setFirstOrderCount(Integer firstOrderCount) {
            this.firstOrderCount = firstOrderCount;
        }

        public Integer getFirstOrderAmount() {
            return firstOrderAmount;
        }

        public void setFirstOrderAmount(Integer firstOrderAmount) {
            this.firstOrderAmount = firstOrderAmount;
        }

        public Integer getRepeatOrderCount() {
            return repeatOrderCount;
        }

        public void setRepeatOrderCount(Integer repeatOrderCount) {
            this.repeatOrderCount = repeatOrderCount;
        }

        public Integer getRepeatOrderAmount() {
            return repeatOrderAmount;
        }

        public void setRepeatOrderAmount(Integer repeatOrderAmount) {
            this.repeatOrderAmount = repeatOrderAmount;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }

    private List<SalesDailyData> data;

    public List<SalesDailyData> getData() {
        return data;
    }

    public void setData(List<SalesDailyData> data) {
        this.data = data;
    }
}
