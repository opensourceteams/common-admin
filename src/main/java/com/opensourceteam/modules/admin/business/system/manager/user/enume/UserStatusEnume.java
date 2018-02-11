package com.opensourceteam.modules.admin.business.system.manager.user.enume;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/1/30.
 * 功能描述:
 */
public enum UserStatusEnume {

    OK(1,"正常状态")
    ;

    UserStatusEnume(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    private Integer value;
    private String name;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
