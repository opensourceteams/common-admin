package com.opensourceteam.modules.common.core.vo.message;

/**
 * 开发人:刘文
 * 日期:  2018/1/24.
 * 功能描述:
 */
public class ResultBack {

    private Boolean success;
    private String code;
    private String message;
    private Object object;


    public ResultBack() {
    }

    public ResultBack(Boolean success, String code, String message) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
