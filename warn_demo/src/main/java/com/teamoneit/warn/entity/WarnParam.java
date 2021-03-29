package com.teamoneit.warn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class WarnParam {
    private String tagHeadId;
    private String sourceId;
    private String sourceName;
    private String townCode;
    private String acquisitionBrand;
    private String constructionUnit;
    private Integer constructionStatus;
    private Integer warnType;
    private String warnTypeValue;
    private Integer warnLevel;
    private Integer procState;
    private List<Integer> procStates;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public String getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(String tagHeadId) {
        this.tagHeadId = tagHeadId;
    }

    public void setDictPrefix(String dictPrefix) {
        this.warnTypeValue = "'" + dictPrefix + "_warn_type'";
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getAcquisitionBrand() {
        return acquisitionBrand;
    }

    public void setAcquisitionBrand(String acquisitionBrand) {
        this.acquisitionBrand = acquisitionBrand;
    }

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public Integer getConstructionStatus() {
        return constructionStatus;
    }

    public void setConstructionStatus(Integer constructionStatus) {
        this.constructionStatus = constructionStatus;
    }

    public Integer getWarnType() {
        return warnType;
    }

    public void setWarnType(Integer warnType) {
        this.warnType = warnType;
    }

    public Integer getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(Integer warnLevel) {
        this.warnLevel = warnLevel;
    }

    public Integer getProcState() {
        return procState;
    }

    public void setProcState(Integer procState) {
        this.procState = procState;
    }

    public List<Integer> getProcStates() {
        return procStates;
    }

    public void setProcStates(List<Integer> procStates) {
        this.procStates = procStates;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
