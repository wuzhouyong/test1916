package com.teamone.project.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.teamone.project.system.domain.entity.PageRequest;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzy
 * @since 2021-02-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("PF_BAS_TAG_HEAD")
public class PfBasTagHead extends PageRequest {

    private static final long serialVersionUID=1L;

    @TableId("TAG_HEAD_ID")
    private String tagHeadId;

    @TableField("TAG_HEAD_NAME")
    private String tagHeadName;

    @TableField("TAG_DESC")
    private String tagDesc;

    @TableField("PARENT_ID")
    private String parentId;

    @TableField("SORT_ID")
    private Integer sortId;

    @TableField("IS_SYS_TAG")
    private Integer isSysTag;

    @TableField("MEMO")
    private String memo;

    /**
     * 删除标记 0:正常 1:已删除
     */
    @TableField("IS_DELETED")
    private Integer isDeleted;

    /**
     * 记录创建者
     */
    @TableField("CREATED_BY")
    private String createdBy;

    @TableField("UPDATED_BY")
    private String updatedBy;

    /**
     * 记录创建日期
     */
    @TableField("DATE_CREATED")
    private LocalDateTime dateCreated;

    /**
     * 记录修改时间
     */
    @TableField("DATE_UPDATED")
    private LocalDateTime dateUpdated;

    @TableField("IS_ACTIVE")
    private Integer isActive;

    @TableField("DB_USER")
    private String dbUser;


}
