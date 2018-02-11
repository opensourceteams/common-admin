package com.opensourceteam.modules.enume;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/1/30.
 * 功能描述:
 */
public enum BusinessTypeEnume {


    Organization(1,"organization","机构"),
    Menu(2,"menu","菜单"),
    Role(3,"role","角色"),
    Permission(4,"permission","权限"),
    User(5,"user","用户"),
    ;

    BusinessTypeEnume(Integer businessId,String prefix, String name) {
        this.businessId = businessId;
        this.prefix = prefix;
        this.name = name;
    }

    private Integer businessId;
    private String prefix;
    private String name;

    /**
     * 得到业务id
     * @param prefix
     * @return
     */
    public static Integer getPrefixBusinessId(String prefix){
        if (prefix != null && !"".equals(prefix)) {
            for(BusinessTypeEnume businessTypeEnume : BusinessTypeEnume.values()){
                if(prefix.contains(businessTypeEnume.getPrefix())){
                    return businessTypeEnume.getBusinessId();
                }
            }
        }
        return 0;
    }


    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

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
