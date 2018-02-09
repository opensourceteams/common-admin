package com.opensourceteam.modules.admin.business.system.manager.role.vo;


import com.opensourceteam.modules.po.admin.SystemRole;

import java.util.List;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/1/29.
 * 功能描述:
 */
public class SystemRoleVo extends SystemRole {

    private String sId;//字符串id
    private String sPid;//父节点Id
    private String iconOpen;
    private String iconClose;
    private String icon;
    private List<String> permissionList;


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

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    public String getsPid() {
        return sPid;
    }

    public void setsPid(String sPid) {
        this.sPid = sPid;
    }
}
