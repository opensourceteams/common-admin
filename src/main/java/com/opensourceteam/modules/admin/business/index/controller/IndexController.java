package com.opensourceteam.modules.admin.business.index.controller;

import com.opensourceteam.modules.admin.base.controller.BaseController;
import com.opensourceteam.modules.admin.business.index.service.main.MainService;
import com.opensourceteam.modules.constant.SystemConstant;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class IndexController extends BaseController {

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    MainService mainService;


    @RequestMapping("/")
    public ModelAndView hello(String message) {

        SecurityUtils.getSubject().getSession().setAttribute(SystemConstant.current_login_user,getCurrentLoginUser());

        ModelAndView modelAndView = new ModelAndView("main");
        logger.info("[IndexController login]");
        modelAndView.addObject("username",getCurrentLoginName());
        modelAndView.addObject("systemMenuList",mainService.getListSystemMenuRelationAll());

        return modelAndView;
    }
}
