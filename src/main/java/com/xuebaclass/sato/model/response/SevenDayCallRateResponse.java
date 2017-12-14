package com.xuebaclass.sato.model.response;

import java.util.List;

/**
 * Created by sunhao on 2017-10-13.
 */
public class SevenDayCallRateResponse {


    public static class Record {
        private String name;
        private Integer callOutNumber = 0;
        private Integer connectedNumber = 0;
        private Integer moreFiveMinutesNumber = 0;
        private Integer moreThreeMinutesNumber = 0;
        private Integer moreOneMinutesNumber = 0;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getCallOutNumber() {
            return callOutNumber;
        }

        public void setCallOutNumber(Integer callOutNumber) {
            this.callOutNumber = callOutNumber;
        }

        public Integer getConnectedNumber() {
            return connectedNumber;
        }

        public void setConnectedNumber(Integer connectedNumber) {
            this.connectedNumber = connectedNumber;
        }

        public Integer getMoreFiveMinutesNumber() {
            return moreFiveMinutesNumber;
        }

        public void setMoreFiveMinutesNumber(Integer moreFiveMinutesNumber) {
            this.moreFiveMinutesNumber = moreFiveMinutesNumber;
        }

        public Integer getMoreThreeMinutesNumber() {
            return moreThreeMinutesNumber;
        }

        public void setMoreThreeMinutesNumber(Integer moreThreeMinutesNumber) {
            this.moreThreeMinutesNumber = moreThreeMinutesNumber;
        }

        public Integer getMoreOneMinutesNumber() {
            return moreOneMinutesNumber;
        }

        public void setMoreOneMinutesNumber(Integer moreOneMinutesNumber) {
            this.moreOneMinutesNumber = moreOneMinutesNumber;
        }
    }

    private List<Record> records;

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
