package com.topcent.project.model;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 企业用户对象 pf_bas_source_user
 *
 * @author ruoyi
 * @date 2020-05-18
 */
public class PfBasSourceUser
{
    private static final long serialVersionUID = 1L;

    private Long sourceUserId;

    private String userName;

    private String nickName;

    private String password;

    private String userFlag;

    private String memo;

    private String sourceId;

    private String delFlag;

    private String loginIp;

    private String createdBy;

    private String updatedBy;

    private String mobile;
    private Integer isBinding;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /** 最后登陆时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date loginDate;

    public void setSourceUserId(Long sourceUserId)
    {
        this.sourceUserId = sourceUserId;
    }

    public Long getSourceUserId()
    {
        return sourceUserId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getNickName()
    {
        return nickName;
    }
    public void setUserFlag(String userFlag)
    {
        this.userFlag = userFlag;
    }

    public String getUserFlag()
    {
        return userFlag;
    }
    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public String getMemo()
    {
        return memo;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }
    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    public String getLoginIp()
    {
        return loginIp;
    }
    public void setLoginDate(Date loginDate)
    {
        this.loginDate = loginDate;
    }

    public Date getLoginDate()
    {
        return loginDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getIsBinding() {
        return isBinding;
    }

    public void setIsBinding(Integer isBinding) {
        this.isBinding = isBinding;
    }

    @Override
    public String toString() {
        return "PfBasSourceUser{" +
                "sourceUserId=" + sourceUserId +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", userFlag='" + userFlag + '\'' +
                ", memo='" + memo + '\'' +
                ", sourceId='" + sourceId + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", mobile='" + mobile + '\'' +
                ", isBinding=" + isBinding +
                ", loginDate=" + loginDate +
                '}';
    }
}