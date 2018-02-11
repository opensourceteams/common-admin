package com.opensourceteam.modules.admin.base.controller;

import com.opensourceteam.modules.admin.business.system.manager.user.service.SystemUserService;
import com.opensourceteam.modules.po.admin.SystemUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/2/10.
 * 功能描述:
 */
@Controller
public class BaseController {

    @Autowired
    public HttpServletRequest request;
    @Autowired
    SystemUserService systemUserService;

    /**
     * 当前登录的用户
     */
    public SystemUser currentLoginUser;

    public SystemUser getCurrentUser(){
        if(currentLoginUser == null){
            String loginId = SecurityUtils.getSubject().getPrincipal().toString();
            currentLoginUser = systemUserService.getUserByLoginId(loginId);
        }
        return currentLoginUser;
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
}
