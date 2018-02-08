package com.opensourceteam.modules.enume;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/1/30.
 * 功能描述:
 */
public enum BusinessTypeEnume {

    User("user","用户")
    ;

    BusinessTypeEnume(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
    }

    private String prefix;
    private String name;


    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
