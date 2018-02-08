package com.opensourceteam.modules.po.admin;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_system_permission")
public class SystemPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限名称
     */
    @Column(name = "permission_name")
    private String permissionName;

    /**
     * 权限代码
     */
    @Column(name = "permission_code")
    private String permissionCode;

    /**
     * 菜单id
     */
    @Column(name = "menu_id")
    private Integer menuId;

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
     * 获取权限名称
     *
     * @return permission_name - 权限名称
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * 设置权限名称
     *
     * @param permissionName 权限名称
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    /**
     * 获取权限代码
     *
     * @return permission_code - 权限代码
     */
    public String getPermissionCode() {
        return permissionCode;
    }

    /**
     * 设置权限代码
     *
     * @param permissionCode 权限代码
     */
    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    /**
     * 获取菜单id
     *
     * @return menu_id - 菜单id
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单id
     *
     * @param menuId 菜单id
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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
        sb.append(", permissionName=").append(permissionName);
        sb.append(", permissionCode=").append(permissionCode);
        sb.append(", menuId=").append(menuId);
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