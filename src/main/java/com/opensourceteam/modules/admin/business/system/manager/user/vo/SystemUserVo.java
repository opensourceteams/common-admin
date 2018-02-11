package com.opensourceteam.modules.admin.business.system.manager.user.vo;

import com.opensourceteam.modules.po.admin.SystemUser;

import java.util.List;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/1/29.
 * 功能描述:
 */
public class SystemUserVo extends SystemUser {

    private String sId;//字符串id
    private String sPid;//父节点Id
    private String iconOpen;
    private String iconClose;
    private String icon;

    private List<String> roleList;

    public String getIconOpen() {
        return iconOpen;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }

    public String getIconClose() {
        return iconClose;
    }

    public void setIconClose(String iconClose) {
        this.iconClose = iconClose;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsPid() {
        return sPid;
    }

    public void setsPid(String sPid) {
        this.sPid = sPid;
    }
}
