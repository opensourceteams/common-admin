package com.opensourceteam.modules.admin.business.index.controller;

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
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);


    @RequestMapping("/main")
    ModelAndView login(String message) {
        ModelAndView modelAndView = new ModelAndView("main");
        logger.info("[IndexController login]");
        modelAndView.addObject("user","admin");
        return modelAndView;
    }
}
