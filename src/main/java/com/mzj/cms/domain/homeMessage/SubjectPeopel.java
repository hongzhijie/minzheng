package com.mzj.cms.domain.homeMessage;

import com.mzj.cms.domain.dto.PageDto;

import java.io.Serializable;

/**
 * @auther: LiuHonghui
 * @date: 2019/3/21 16:49
 * @classname: SubjectPeopel
 */
public class SubjectPeopel extends PageDto implements Serializable {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 地址
     */
    private String address;
    /**
     * 身份证号
     */
    private String cardNum;
    /**
     * 帮扶措施
     */
    private String helpType;
    /**
     * 年增收
     */
    private String addMoney;
    /**
     * 是否满意
     */
    private String isGood;
    /**
     * 主体ID
     */
    private Integer subjectId;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCardNum() {
        return cardNum;
    }

    public String getHelpType() {
        return helpType;
    }

    public String getAddMoney() {
        return addMoney;
    }

    public String getIsGood() {
        return isGood;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public void setHelpType(String helpType) {
        this.helpType = helpType;
    }

    public void setAddMoney(String addMoney) {
        this.addMoney = addMoney;
    }

    public void setIsGood(String isGood) {
        this.isGood = isGood;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
