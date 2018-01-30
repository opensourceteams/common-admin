package com.opensourceteam.modules.admin.business.system.manager.menu.vo;

import com.opensourceteam.modules.po.admin.SystemMenu;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/1/30.
 * 功能描述:
 */
public class SystemMenuVo extends SystemMenu {

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
}
