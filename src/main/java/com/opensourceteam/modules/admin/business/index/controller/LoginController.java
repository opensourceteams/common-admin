package com.opensourceteam.modules.admin.business.index.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    String login(String message) {
        logger.info("[IndexController login]");

        return "redirect:main";
    }
}
