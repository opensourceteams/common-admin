package com.opensourceteam.modules.enume;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/1/28.
 * 功能描述:
 */
public enum IconTypeEnume {
    Organization('1',"机构","/static/modules/ztree/v3.5.32/css/zTreeStyle/img/diy/10_open.png","/static/modules/ztree/v3.5.32/css/zTreeStyle/img/diy/10_close.png"),
    Department('2',"部门","/static/modules/ztree/v3.5.32/css/zTreeStyle/img/diy/11_open.png","/static/modules/ztree/v3.5.32/css/zTreeStyle/img/diy/11_close.png"),
    Group('3',"组","/static/modules/ztree/v3.5.32/css/zTreeStyle/img/diy/12_open.png","/static/modules/ztree/v3.5.32/css/zTreeStyle/img/diy/12_close.png"),
    Employee('4',"人员", "/static/modules/ztree/v3.5.32/css/zTreeStyle/img/diy/user_red.png", "/static/modules/ztree/v3.5.32/css/zTreeStyle/img/diy/user_blue.png"),
    Menu('5',"菜单","/static/modules/ztree/v3.5.32/css/zTreeStyle/img/diy/menu_red.png","/static/modules/ztree/v3.5.32/css/zTreeStyle/img/diy/menu_blue.png"),
    Role('6',"角色","/static/modules/ztree/v3.5.32/css/zTreeStyle/img/diy/role_red.png","/static/modules/ztree/v3.5.32/css/zTreeStyle/img/diy/role_blue.png"),
    Permision('7',"权限","/static/modules/ztree/v3.5.32/css/zTreeStyle/img/diy/permission_red.png","/static/modules/ztree/v3.5.32/css/zTreeStyle/img/diy/permission_blue.png"),
    ;

    private char value;
    private String name;
    private String openUrl;
    private String closeUrl;

    IconTypeEnume(char value, String name, String openUrl, String closeUrl) {
        this.value = value;
        this.name = name;
        this.openUrl = openUrl;
        this.closeUrl = closeUrl;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenUrl() {
        return openUrl;
    }

    public void setOpenUrl(String openUrl) {
        this.openUrl = openUrl;
    }

    public String getCloseUrl() {
        return closeUrl;
    }

    public void setCloseUrl(String closeUrl) {
        this.closeUrl = closeUrl;
    }
}
