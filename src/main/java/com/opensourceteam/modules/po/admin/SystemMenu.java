package com.opensourceteam.modules.po.admin;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_system_menu")
public class SystemMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select _nextval('t_system_menu')")
    private Integer id;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 菜单url
     */
    @Column(name = "menu_url")
    private String menuUrl;

    @Column(name = "parent_ids")
    private String parentIds;

    /**
     * 菜单类型(1:菜单2:权限节点)
     */
    @Column(name = "type_code")
    private String typeCode;

    /**
     * 菜单是第几级
     */
    @Column(name = "levle_num")
    private Integer levleNum;

    @Column(name = "create_date")
    private Date createDate;

    private Integer creator;

    @Column(name = "update_date")
    private Date updateDate;

    private Integer updator;

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
     * 获取菜单名称
     *
     * @return menu_name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
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
     * 获取菜单url
     *
     * @return menu_url - 菜单url
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * 设置菜单url
     *
     * @param menuUrl 菜单url
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
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
     * 获取菜单类型(1:菜单2:权限节点)
     *
     * @return type_code - 菜单类型(1:菜单2:权限节点)
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 设置菜单类型(1:菜单2:权限节点)
     *
     * @param typeCode 菜单类型(1:菜单2:权限节点)
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * 获取菜单是第几级
     *
     * @return levle_num - 菜单是第几级
     */
    public Integer getLevleNum() {
        return levleNum;
    }

    /**
     * 设置菜单是第几级
     *
     * @param levleNum 菜单是第几级
     */
    public void setLevleNum(Integer levleNum) {
        this.levleNum = levleNum;
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
     * @return update_date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return updator
     */
    public Integer getUpdator() {
        return updator;
    }

    /**
     * @param updator
     */
    public void setUpdator(Integer updator) {
        this.updator = updator;
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
        sb.append(", menuName=").append(menuName);
        sb.append(", parentId=").append(parentId);
        sb.append(", menuUrl=").append(menuUrl);
        sb.append(", parentIds=").append(parentIds);
        sb.append(", typeCode=").append(typeCode);
        sb.append(", levleNum=").append(levleNum);
        sb.append(", createDate=").append(createDate);
        sb.append(", creator=").append(creator);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updator=").append(updator);
        sb.append(", isDel=").append(isDel);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}