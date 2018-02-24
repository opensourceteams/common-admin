package com.opensourceteam.modules.admin.business.system.manager.menu.vo;

import com.opensourceteam.modules.po.admin.SystemMenu;

import java.util.ArrayList;
import java.util.List;

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
    private Integer navigationId;
    private List<SystemMenuVo> childList = new ArrayList<>();

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

    public List<SystemMenuVo> getChildList() {
        return childList;
    }

    public void setChildList(List<SystemMenuVo> childList) {
        this.childList = childList;
    }

    public Integer getNavigationId() {
        return navigationId;
    }

    public void setNavigationId(Integer navigationId) {
        this.navigationId = navigationId;
    }
}
