package com.opensourceteam.modules.admin.business.test.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/2/10.
 * 功能描述:
 */
@Controller
@RequestMapping
public class TestController {

    @RequestMapping("/test")
    ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView("/test/test");
        return modelAndView;
    }

    @RequestMapping("/test1")
    ModelAndView test1() {
        ModelAndView modelAndView = new ModelAndView("/test/test1");
        return modelAndView;
    }

}
