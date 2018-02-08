package com.opensourceteam.modules.po.admin;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_system_organization")
public class TSystemOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 机构名称
     */
    private String name;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "parent_ids")
    private String parentIds;

    @Column(name = "level_num")
    private Integer levelNum;

    /**
     * 机构类型(1:机构 2:部门 3:组)
     */
    @Column(name = "org_type")
    private String orgType;

    @Column(name = "create_date")
    private Date createDate;

    private Integer creator;

    @Column(name = "is_del")
    private Boolean isDel;

    /**
     * 备注
     */
    private String remark;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取机构名称
     *
     * @return name - 机构名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置机构名称
     *
     * @param name 机构名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return parent_id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return parent_ids
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * @param parentIds
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    /**
     * @return level_num
     */
    public Integer getLevelNum() {
        return levelNum;
    }

    /**
     * @param levelNum
     */
    public void setLevelNum(Integer levelNum) {
        this.levelNum = levelNum;
    }

    /**
     * 获取机构类型(1:机构 2:部门 3:组)
     *
     * @return org_type - 机构类型(1:机构 2:部门 3:组)
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * 设置机构类型(1:机构 2:部门 3:组)
     *
     * @param orgType 机构类型(1:机构 2:部门 3:组)
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return creator
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * @return is_del
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     * @param isDel
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", parentId=").append(parentId);
        sb.append(", parentIds=").append(parentIds);
        sb.append(", levelNum=").append(levelNum);
        sb.append(", orgType=").append(orgType);
        sb.append(", createDate=").append(createDate);
        sb.append(", creator=").append(creator);
        sb.append(", isDel=").append(isDel);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}