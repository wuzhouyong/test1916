package com.teamoneit.warn.entity;

import java.math.BigDecimal;
import java.util.Date;

public class WarnResult {
    private String taskId;
    private String targetId;
    private String targetName;
    private String townStreetFullName;
    private String townStreetCode;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Integer level1;
    private Integer level;
    private Integer warnType;
    private String brief;
    private String abnormalInfo;
    private Integer procState;
    private String createTime;
    private String detail;
    private String creatorName;
    private String taskStatus;
    private Integer implement = 1;
    private Integer timeLimit = 168;
    private Integer taskType = 1;
    private Integer taskClassify;

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getTownStreetFullName() {
        return townStreetFullName;
    }

    public void setTownStreetFullName(String townStreetFullName) {
        this.townStreetFullName = townStreetFullName;
    }

    public String getTownStreetCode() {
        return townStreetCode;
    }

    public void setTownStreetCode(String townStreetCode) {
        this.townStreetCode = townStreetCode;
    }

    public BigDecimal getLongitude() {
        if (longitude == null) return new BigDecimal(0);
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        if (latitude == null) return new BigDecimal(0);
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Integer getLevel() {
        if (level1 != null) return level1;
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel1() {
        return level1;
    }

    public void setLevel1(Integer level1) {
        this.level1 = level1;
    }

    public Integer getWarnType() {
        return warnType;
    }

    public void setWarnType(Integer warnType) {
        this.warnType = warnType;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getAbnormalInfo() {
        return abnormalInfo;
    }

    public void setAbnormalInfo(String abnormalInfo) {
        this.abnormalInfo = abnormalInfo;
    }

    public Integer getProcState() {
        return procState;
    }

    public void setProcState(Integer procState) {
        this.procState = procState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Integer getImplement() {
        return implement;
    }

    public void setImplement(Integer implement) {
        this.implement = implement;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getTaskClassify() {
        return taskClassify;
    }

    public void setTaskClassify(Integer taskClassify) {
        this.taskClassify = taskClassify;
    }

}
