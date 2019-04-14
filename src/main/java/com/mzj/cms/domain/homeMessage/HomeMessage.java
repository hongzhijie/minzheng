package com.mzj.cms.domain.homeMessage;

import com.mzj.cms.domain.dto.PageDto;

import java.io.Serializable;
import java.util.Date;

/**
 * @auther: LiuHonghui
 * @date: 2019/3/19 15:22
 * @classname: HomeMessage
 */
public class HomeMessage extends PageDto implements Serializable {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 户主姓名
     */
    private String homeMessage;
    /**
     * 身份证号
     */
    private String cardNum;
    /**
     * 核对成员
     */
    private String checkPeople;
    /**
     * 审批日期
     */
    private String approvalDate;
    /**
     * 核对日期
     */
    private String chcekDate;
    /**
     * 工商登记
     */
    private String businessMessgae;
    /**
     * 税务登记
     */
    private String taxation;
    /**
     * 机动车登记
     */
    private String vehicle;
    /**
     * 人社退休
     */
    private String retire;
    /**
     * 人社参保
     */
    private String socialSecurity;
    /**
     * 财政供养人员
     */
    private String supportPeopel;
    /**
     * 房屋产权登记
     */
    private String houseProperty;
    /**
     * 公积金缴纳贷款
     */
    private String accumulationFund;
    /**
     * 失业人员
     */
    private String unemployedPersonnel;
    /**
     * 交通营运
     */
    private String trafficChecking;
    /**
     * 银行
     */
    private String bank;

    /**
     * 证券
     */
    private String securities;

    public Integer getId() {
        return id;
    }

    public String getHomeMessage() {
        return homeMessage;
    }

    public String getCardNum() {
        return cardNum;
    }

    public String getCheckPeople() {
        return checkPeople;
    }


    public String getBusinessMessgae() {
        return businessMessgae;
    }

    public String getTaxation() {
        return taxation;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getRetire() {
        return retire;
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public String getSupportPeopel() {
        return supportPeopel;
    }

    public String getHouseProperty() {
        return houseProperty;
    }

    public String getAccumulationFund() {
        return accumulationFund;
    }

    public String getUnemployedPersonnel() {
        return unemployedPersonnel;
    }

    public String getTrafficChecking() {
        return trafficChecking;
    }

    public String getBank() {
        return bank;
    }

    public String getSecurities() {
        return securities;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setHomeMessage(String homeMessage) {
        this.homeMessage = homeMessage;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public void setCheckPeople(String checkPeople) {
        this.checkPeople = checkPeople;
    }


    public void setBusinessMessgae(String businessMessgae) {
        this.businessMessgae = businessMessgae;
    }

    public void setTaxation(String taxation) {
        this.taxation = taxation;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public void setRetire(String retire) {
        this.retire = retire;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public void setSupportPeopel(String supportPeopel) {
        this.supportPeopel = supportPeopel;
    }

    public void setHouseProperty(String houseProperty) {
        this.houseProperty = houseProperty;
    }

    public void setAccumulationFund(String accumulationFund) {
        this.accumulationFund = accumulationFund;
    }

    public void setUnemployedPersonnel(String unemployedPersonnel) {
        this.unemployedPersonnel = unemployedPersonnel;
    }

    public void setTrafficChecking(String trafficChecking) {
        this.trafficChecking = trafficChecking;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setSecurities(String securities) {
        this.securities = securities;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public String getChcekDate() {
        return chcekDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public void setChcekDate(String chcekDate) {
        this.chcekDate = chcekDate;
    }
}
