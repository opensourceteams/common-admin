package com.opensourceteam.modules.admin.business.system.manager.organization.controller;

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
@RequestMapping("/common/admin/system_manager/organization")
public class OrganizationController {

    Logger logger = LoggerFactory.getLogger(OrganizationController.class);


    @RequestMapping("/index")
    ModelAndView index(String message) {
        ModelAndView modelAndView = new ModelAndView("common/admin/system_manager/organization/organizationIndex");
        logger.info("[OrganizationController index]");
        return modelAndView;
    }
}
