package com.mzj.cms.domain.homeMessage;

import com.mzj.cms.domain.dto.PageDto;

import java.io.Serializable;

/**
 * @auther: LiuHonghui
 * @date: 2019/3/21 16:33
 * @classname: SubjectMessage
 */
public class SubjectMessage extends PageDto implements Serializable {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 主体名称
     */
    private String subjectName;
    /**
     * 主体法人
     */
    private String subjectManager;
    /**
     * 贷款金额
     */
    private String loanNum;
    /**
     * 应帮户数
     */
    private String helpNum;
    /**
     * 达标户数
     */
    private String standardNum;
    /**
     * 达标率
     */
    private String complianceRate;
    /**
     * 合格定性
     */
    private String qualifyingQualitative;
    /**
     * 经营规模
     */
    private String scaleOperation;
    /**
     * 是否正常经营
     */
    private String isNormal;

    public Integer getId() {
        return id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectManager() {
        return subjectManager;
    }

    public String getLoanNum() {
        return loanNum;
    }

    public String getHelpNum() {
        return helpNum;
    }

    public String getStandardNum() {
        return standardNum;
    }

    public String getComplianceRate() {
        return complianceRate;
    }

    public String getQualifyingQualitative() {
        return qualifyingQualitative;
    }

    public String getScaleOperation() {
        return scaleOperation;
    }

    public String getIsNormal() {
        return isNormal;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setSubjectManager(String subjectManager) {
        this.subjectManager = subjectManager;
    }

    public void setLoanNum(String loanNum) {
        this.loanNum = loanNum;
    }

    public void setHelpNum(String helpNum) {
        this.helpNum = helpNum;
    }

    public void setStandardNum(String standardNum) {
        this.standardNum = standardNum;
    }

    public void setComplianceRate(String complianceRate) {
        this.complianceRate = complianceRate;
    }

    public void setQualifyingQualitative(String qualifyingQualitative) {
        this.qualifyingQualitative = qualifyingQualitative;
    }

    public void setScaleOperation(String scaleOperation) {
        this.scaleOperation = scaleOperation;
    }

    public void setIsNormal(String isNormal) {
        this.isNormal = isNormal;
    }
}
