package com.opensourceteam.modules.admin.business.index.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 开发人:刘文
 * 日期:  2018/1/25.
 * 功能描述:
 */
@Controller
@RequestMapping
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping("/login")
    ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @RequestMapping("/doLogin")
    ModelAndView doLogin(String loginId,String password) {
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        UsernamePasswordToken token = new UsernamePasswordToken("admin","000000");
        SecurityUtils.getSubject().login(token);
        if( SecurityUtils.getSubject().isAuthenticated()){
            modelAndView = new ModelAndView("redirect:/main");
        }


        return modelAndView;
    }
}
