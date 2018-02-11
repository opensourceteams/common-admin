package com.opensourceteam.modules.admin.base.service;

import com.opensourceteam.modules.admin.business.system.manager.user.service.SystemUserService;
import com.opensourceteam.modules.constant.SystemConstant;
import com.opensourceteam.modules.po.admin.SystemUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
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
     * 当前登录的用户
     */
    public SystemUser currentLoginUser;

    @Autowired
    SystemUserService systemUserService;

    public SystemUser getCurrentUser(){
        Object object = SecurityUtils.getSubject().getSession().getAttribute(SystemConstant.current_login_user);
        if(object !=null && object instanceof SystemUser){
            return (SystemUser)object;
        }
        throw new AuthorizationException();
    }
    /**
     * 得到登录名
     * @return
     */
    public String getCurrentLoginId(){
        SystemUser systemUser = getCurrentUser();
        return getCurrentUser().getLoginId();
    }

    /**
     * 得到当前登录用户的姓名
     * @return
     */
    public String getCurrentLoginName(){
        SystemUser systemUser = getCurrentUser();
        return getCurrentUser().getName();
    }

    /**
     * 得到登录用户id
     * @return
     */
    public Integer getCurrentUserId(){
        SystemUser systemUser = getCurrentUser();
        return getCurrentUser().getId();
    }

    public HttpServletRequest getRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

}
