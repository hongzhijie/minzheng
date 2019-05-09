package com.mzj.cms.domain.homeMessage;

import com.mzj.cms.domain.dto.PageDto;

import java.io.Serializable;

/**
 * @auther: hzj
 * @Description 文件存储实体
 * @date: 2019/5/9 15:22
 * @classname: MzFile
 */
public class MzFile extends PageDto implements Serializable {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 外键类型0-home_message表主键1-subject_message表主键2-subject_people表主键
     */
    private Integer openType;

    /**
     * 关联外键主键
     */
    private Integer openId;

    /**
     * 文件类型0-图片1-录音
     */
    private Integer fileType;

    /**
     * 文件存储地址
     */
    private String fileUrl;

    /**
     * 状态0-正常1-删除
     */
    private Integer status;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 删除时间
     */
    private String deleteTime;

    public Integer getOpenType() {
        return openType;
    }

    public void setOpenType(Integer openType) {
        this.openType = openType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOpenId() {
        return openId;
    }

    public void setOpenId(Integer openId) {
        this.openId = openId;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }
}
