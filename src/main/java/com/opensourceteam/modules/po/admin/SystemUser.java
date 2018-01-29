package com.opensourceteam.modules.po.admin;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_system_user")
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select _nextval('t_system_user')")
    private Integer id;

    /**
     * 登录名
     */
    @Column(name = "login_id")
    private String loginId;

    /**
     * 登录密码
     */
    @Column(name = "login_pwd")
    private String loginPwd;

    /**
     * 用户名称
     */
    private String name;

    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 机构ID
     */
    @Column(name = "org_id")
    private Integer orgId;

    @Column(name = "parent_ids")
    private String parentIds;

    /**
     * 用户层级
     */
    @Column(name = "level_num")
    private Integer levelNum;

    @Column(name = "type_code")
    private String typeCode;

    /**
     * 用户状态码 (1:可用   2:锁定  3:过期)
     */
    @Column(name = "status_code")
    private String statusCode;

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
     * 获取登录名
     *
     * @return login_id - 登录名
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * 设置登录名
     *
     * @param loginId 登录名
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * 获取登录密码
     *
     * @return login_pwd - 登录密码
     */
    public String getLoginPwd() {
        return loginPwd;
    }

    /**
     * 设置登录密码
     *
     * @param loginPwd 登录密码
     */
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    /**
     * 获取用户名称
     *
     * @return name - 用户名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名称
     *
     * @param name 用户名称
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
     * 获取机构ID
     *
     * @return org_id - 机构ID
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * 设置机构ID
     *
     * @param orgId 机构ID
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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
     * 获取用户层级
     *
     * @return level_num - 用户层级
     */
    public Integer getLevelNum() {
        return levelNum;
    }

    /**
     * 设置用户层级
     *
     * @param levelNum 用户层级
     */
    public void setLevelNum(Integer levelNum) {
        this.levelNum = levelNum;
    }

    /**
     * @return type_code
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * @param typeCode
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * 获取用户状态码 (1:可用   2:锁定  3:过期)
     *
     * @return status_code - 用户状态码 (1:可用   2:锁定  3:过期)
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * 设置用户状态码 (1:可用   2:锁定  3:过期)
     *
     * @param statusCode 用户状态码 (1:可用   2:锁定  3:过期)
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
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
        sb.append(", loginId=").append(loginId);
        sb.append(", loginPwd=").append(loginPwd);
        sb.append(", name=").append(name);
        sb.append(", parentId=").append(parentId);
        sb.append(", orgId=").append(orgId);
        sb.append(", parentIds=").append(parentIds);
        sb.append(", levelNum=").append(levelNum);
        sb.append(", typeCode=").append(typeCode);
        sb.append(", statusCode=").append(statusCode);
        sb.append(", createDate=").append(createDate);
        sb.append(", creator=").append(creator);
        sb.append(", isDel=").append(isDel);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}