package com.opensourceteam.modules.admin.business.system.manager.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/1/26.
 * 功能描述:
 */
@Controller
@RequestMapping("/common/admin/system_manager/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping("/index")
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("common/admin/system_manager/user/userIndex");
        logger.info("[OrganizationController index]");
        return modelAndView;
    }
}
