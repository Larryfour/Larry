package com.xuebaclass.sato.model;

import java.util.Date;

/**
 * Created by sunhao on 2017-08-17.
 */
public class Customer extends AbstractPersistable{


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


    private String contactName;
    private String contactMobile;
    private Integer xuebaNo;
    private String name;
    private String mobile;
    private Gender gender;
    private String qq;
    private String parents;
    private String parentsMobile;
    private String province;
    private String city;
    private String district;
    private String school;
    private String grade;
    private String gradeNote;
    private String teachingAterial;
    private String teachingAterialNote;
    private Integer scores;
    private Integer fullMarks;
    private String comment;
    private Boolean tutorialFlag;
    private String learningProcess;
    private String nextTest;
    private Date nextTestDate;
    private String answerInterval;
    private Integer ownedSalesID;
    private String ownedSalesName;
    private String ownedSalesUserName;
    private String source;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public Integer getXuebaNo() {
        return xuebaNo;
    }

    public void setXuebaNo(Integer xuebaNo) {
        this.xuebaNo = xuebaNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public String getParentsMobile() {
        return parentsMobile;
    }

    public void setParentsMobile(String parentsMobile) {
        this.parentsMobile = parentsMobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGradeNote() {
        return gradeNote;
    }

    public void setGradeNote(String gradeNote) {
        this.gradeNote = gradeNote;
    }

    public String getTeachingAterial() {
        return teachingAterial;
    }

    public void setTeachingAterial(String teachingAterial) {
        this.teachingAterial = teachingAterial;
    }

    public String getTeachingAterialNote() {
        return teachingAterialNote;
    }

    public void setTeachingAterialNote(String teachingAterialNote) {
        this.teachingAterialNote = teachingAterialNote;
    }

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }

    public Integer getFullMarks() {
        return fullMarks;
    }

    public void setFullMarks(Integer fullMarks) {
        this.fullMarks = fullMarks;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getTutorialFlag() {
        return tutorialFlag;
    }

    public void setTutorialFlag(Boolean tutorialFlag) {
        this.tutorialFlag = tutorialFlag;
    }

    public String getLearningProcess() {
        return learningProcess;
    }

    public void setLearningProcess(String learningProcess) {
        this.learningProcess = learningProcess;
    }

    public String getNextTest() {
        return nextTest;
    }

    public void setNextTest(String nextTest) {
        this.nextTest = nextTest;
    }

    public Date getNextTestDate() {
        return nextTestDate;
    }

    public void setNextTestDate(Date nextTestDate) {
        this.nextTestDate = nextTestDate;
    }

    public String getAnswerInterval() {
        return answerInterval;
    }

    public void setAnswerInterval(String answerInterval) {
        this.answerInterval = answerInterval;
    }

    public Integer getOwnedSalesID() {
        return ownedSalesID;
    }

    public void setOwnedSalesID(Integer ownedSalesID) {
        this.ownedSalesID = ownedSalesID;
    }

    public String getOwnedSalesName() {
        return ownedSalesName;
    }

    public void setOwnedSalesName(String ownedSalesName) {
        this.ownedSalesName = ownedSalesName;
    }

    public String getOwnedSalesUserName() {
        return ownedSalesUserName;
    }

    public void setOwnedSalesUserName(String ownedSalesUserName) {
        this.ownedSalesUserName = ownedSalesUserName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
