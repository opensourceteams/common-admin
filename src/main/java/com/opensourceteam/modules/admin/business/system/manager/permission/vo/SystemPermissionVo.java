package com.opensourceteam.modules.admin.business.system.manager.permission.vo;

import com.opensourceteam.modules.po.admin.SystemPermission;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/1/29.
 * 功能描述:
 */
public class SystemPermissionVo extends SystemPermission {

    private String sId;//字符串id
    private String iconOpen;
    private String iconClose;
    private String icon;

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
}
