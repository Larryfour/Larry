package com.xuebaclass.sato.common;

import com.mysql.jdbc.StringUtils;

import java.util.Calendar;

/**
 * Created by wenyong on 2017/11/3.
 */
public enum Grade {

    PRIMARY_SIX("小六", 6),

    JUNIOR_ONE("初一", 7),
    JUNIOR_TWO("初二", 8),
    JUNIOR_THREE("初三", 9),

    SENIOR_ONE("高一", 10),
    SENIOR_TWO("高二", 11),
    SENIOR_THREE("高三", 12),

    GRADE_OTHER("其他", 100);

    public static final String GRADE_F_NAME = "年级";
    public static final String NCEETIME_F_NAME = "高考时间";

    private String name;
    private int code;

    private Grade(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    /////////////////////////////////////////////////

    // 从年级转为高考时间
    public static int getNCEETimeFromGradeName(String gradeName) {
        int nceeTime = 0;

        if (StringUtils.isNullOrEmpty(gradeName)) {
            return nceeTime;
        }

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;

        for (Grade g : Grade.values()) {
            int delta = 12 - g.getCode();
            if (gradeName.equals(g.getName())) {
                if (g == GRADE_OTHER) {
                    nceeTime = 1;
                } else {
                    nceeTime = month >= 9 ? year + delta + 1 : year + delta;
                }
            }
        }
        return nceeTime;
    }

    public static Grade getGradeFromNCEETime(int nceetime) {
        Grade grade = Grade.GRADE_OTHER;

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;

        if (nceetime == year) {
            grade = SENIOR_THREE;
        } else if (nceetime == year+1) {
            grade = month >= 9 ? SENIOR_THREE : SENIOR_TWO;
        } else if (nceetime == year+2) {
            grade = month >= 9 ? SENIOR_TWO : SENIOR_ONE;
        } else if (nceetime == year+3) {
            grade = month >= 9 ? SENIOR_ONE : JUNIOR_THREE;
        } else if (nceetime == year+4) {
            grade = month >= 9 ? JUNIOR_THREE : JUNIOR_TWO;
        } else if (nceetime == year+5) {
            grade = month >= 9 ? JUNIOR_TWO : JUNIOR_ONE;
        } else if (nceetime == year+6) {
            grade = month >= 9 ? JUNIOR_ONE : PRIMARY_SIX;
        } else if (nceetime >= year+7) {
            grade = month >= 9 ? PRIMARY_SIX : PRIMARY_SIX;
        } else if (nceetime == 1) { // 用户选择其它
            grade = GRADE_OTHER;
        } else if (nceetime == 0) { // 未知状态
            grade = GRADE_OTHER;
        } else {
            grade = GRADE_OTHER;   // 大学
        }

        return grade;
    }

//    public static void main(String[] args) {
//        System.out.println(getNCEETimeFromGradeName(JUNIOR_TWO.getName()));
//    }

}
