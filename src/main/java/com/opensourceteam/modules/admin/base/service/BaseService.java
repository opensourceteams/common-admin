package com.opensourceteam.modules.admin.base.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/1/30.
 * 功能描述:
 */
@Service
public class BaseService{


    /**
     * 得到当前登录用户ID
     * @return
     */
    public Integer getCurrentUserId(){
        return 2;
    }


    public HttpServletRequest getRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

}
