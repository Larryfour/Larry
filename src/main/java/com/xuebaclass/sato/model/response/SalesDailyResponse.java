package com.xuebaclass.sato.model.response;

import java.util.List;

/**
 * Created by sunhao on 2017-10-13.
 */
public class SalesDailyResponse {

    public static class DailyData {
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
        private String salesId;
        private String salesName;
        private Integer groupId;

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

        public String getSalesId() {
            return salesId;
        }

        public void setSalesId(String salesId) {
            this.salesId = salesId;
        }

        public Integer getGroupId() {
            return groupId;
        }

        public void setGroupId(Integer groupId) {
            this.groupId = groupId;
        }

        public String getSalesName() {
            return salesName;
        }

        public void setSalesName(String salesName) {
            this.salesName = salesName;
        }
    }

    public static class DailyNew {
        private Integer callerCount;
        private Integer distributionCount;
        private Integer completedCount;
        private Integer orderCount;
        private Integer orderAmount;

        public Integer getCallerCount() {
            return callerCount;
        }

        public void setCallerCount(Integer callerCount) {
            this.callerCount = callerCount;
        }

        public Integer getDistributionCount() {
            return distributionCount;
        }

        public void setDistributionCount(Integer distributionCount) {
            this.distributionCount = distributionCount;
        }

        public Integer getCompletedCount() {
            return completedCount;
        }

        public void setCompletedCount(Integer completedCount) {
            this.completedCount = completedCount;
        }

        public Integer getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(Integer orderCount) {
            this.orderCount = orderCount;
        }

        public Integer getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(Integer orderAmount) {
            this.orderAmount = orderAmount;
        }
    }

    public static class DailyReNew {
        private Integer callerCount;
        private Integer orderCount;
        private Integer orderAmount;

        public Integer getCallerCount() {
            return callerCount;
        }

        public void setCallerCount(Integer callerCount) {
            this.callerCount = callerCount;
        }

        public Integer getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(Integer orderCount) {
            this.orderCount = orderCount;
        }

        public Integer getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(Integer orderAmount) {
            this.orderAmount = orderAmount;
        }
    }

    public static class MonthData {
        private Integer targetOrderCount;
        private Integer completedOrderCount;
        private String orderCompletedPercent;
        private Integer targetAmount;
        private Integer completedAmount;
        private String amountCompletedPercent;

        public Integer getTargetOrderCount() {
            return targetOrderCount;
        }

        public void setTargetOrderCount(Integer targetOrderCount) {
            this.targetOrderCount = targetOrderCount;
        }

        public Integer getCompletedOrderCount() {
            return completedOrderCount;
        }

        public void setCompletedOrderCount(Integer completedOrderCount) {
            this.completedOrderCount = completedOrderCount;
        }

        public String getOrderCompletedPercent() {
            return orderCompletedPercent;
        }

        public void setOrderCompletedPercent(String orderCompletedPercent) {
            this.orderCompletedPercent = orderCompletedPercent;
        }

        public Integer getTargetAmount() {
            return targetAmount;
        }

        public void setTargetAmount(Integer targetAmount) {
            this.targetAmount = targetAmount;
        }

        public Integer getCompletedAmount() {
            return completedAmount;
        }

        public void setCompletedAmount(Integer completedAmount) {
            this.completedAmount = completedAmount;
        }

        public String getAmountCompletedPercent() {
            return amountCompletedPercent;
        }

        public void setAmountCompletedPercent(String amountCompletedPercent) {
            this.amountCompletedPercent = amountCompletedPercent;
        }
    }

    public static class PersonalMonthData {
        private String salesId;
        private Integer groupId;
        private String name;
        private Integer targetAmount;
        private Integer completedAmount;
        private String amountCompletedPercent;
        private Integer targetOrderCount;
        private Integer completedOrderCount;
        private String orderCompletedPercent;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getTargetAmount() {
            return targetAmount;
        }

        public void setTargetAmount(Integer targetAmount) {
            this.targetAmount = targetAmount;
        }

        public Integer getCompletedAmount() {
            return completedAmount;
        }

        public void setCompletedAmount(Integer completedAmount) {
            this.completedAmount = completedAmount;
        }

        public Integer getTargetOrderCount() {
            return targetOrderCount;
        }

        public void setTargetOrderCount(Integer targetOrderCount) {
            this.targetOrderCount = targetOrderCount;
        }

        public Integer getCompletedOrderCount() {
            return completedOrderCount;
        }

        public void setCompletedOrderCount(Integer completedOrderCount) {
            this.completedOrderCount = completedOrderCount;
        }

        public String getSalesId() {
            return salesId;
        }

        public void setSalesId(String salesId) {
            this.salesId = salesId;
        }

        public String getAmountCompletedPercent() {
            return amountCompletedPercent;
        }

        public void setAmountCompletedPercent(String amountCompletedPercent) {
            this.amountCompletedPercent = amountCompletedPercent;
        }

        public String getOrderCompletedPercent() {
            return orderCompletedPercent;
        }

        public void setOrderCompletedPercent(String orderCompletedPercent) {
            this.orderCompletedPercent = orderCompletedPercent;
        }

        public Integer getGroupId() {
            return groupId;
        }

        public void setGroupId(Integer groupId) {
            this.groupId = groupId;
        }
    }

    public static class TeacherRepeatOrder{
        Integer orderCount;
        Integer orderAmount;
        Integer totalAmount;

        public Integer getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(Integer orderCount) {
            this.orderCount = orderCount;
        }

        public Integer getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(Integer orderAmount) {
            this.orderAmount = orderAmount;
        }

        public Integer getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Integer totalAmount) {
            this.totalAmount = totalAmount;
        }
    }

    public static class Daily {
        private DailyNew dailyNew;
        private DailyReNew dailyReNew;

        public DailyNew getDailyNew() {
            return dailyNew;
        }

        public void setDailyNew(DailyNew dailyNew) {
            this.dailyNew = dailyNew;
        }

        public DailyReNew getDailyReNew() {
            return dailyReNew;
        }

        public void setDailyReNew(DailyReNew dailyReNew) {
            this.dailyReNew = dailyReNew;
        }
    }

    public static class Month {
        private MonthData monthNews;
        private MonthData monthReNews;
        private TeacherRepeatOrder teacherRepeatOrder;

        public MonthData getMonthNews() {
            return monthNews;
        }

        public void setMonthNews(MonthData monthNews) {
            this.monthNews = monthNews;
        }

        public MonthData getMonthReNews() {
            return monthReNews;
        }

        public void setMonthReNews(MonthData monthReNews) {
            this.monthReNews = monthReNews;
        }

        public TeacherRepeatOrder getTeacherRepeatOrder() {
            return teacherRepeatOrder;
        }

        public void setTeacherRepeatOrder(TeacherRepeatOrder teacherRepeatOrder) {
            this.teacherRepeatOrder = teacherRepeatOrder;
        }
    }

    private Daily daily;
    private List<DailyData> personalDaily;
    private Month month;
    private List<PersonalMonthData> personalMonth;


    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public List<DailyData> getPersonalDaily() {
        return personalDaily;
    }

    public void setPersonalDaily(List<DailyData> personalDaily) {
        this.personalDaily = personalDaily;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public List<PersonalMonthData> getPersonalMonth() {
        return personalMonth;
    }

    public void setPersonalMonth(List<PersonalMonthData> personalMonth) {
        this.personalMonth = personalMonth;
    }
}
