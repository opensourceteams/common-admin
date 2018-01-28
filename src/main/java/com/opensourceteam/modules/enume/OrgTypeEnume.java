package com.opensourceteam.modules.enume;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/1/28.
 * 功能描述:
 */
public enum  OrgTypeEnume {
    Organization('1',"机构"),
    Department('2',"部门"),
    group('3',"组"),
    ;

    private char value;
    private String name;

    OrgTypeEnume(char value, String name) {
        this.value = value;
        this.name = name;
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
}
