package com.opensourceteam.modules.admin.business.finance.check.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/1/28.
 * 功能描述:
 */
@Controller
@RequestMapping("/finance/check")
public class CheckController {
    @RequestMapping("/index")
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/common/admin/finance/check/checkIndex1");
        return modelAndView;
    }
}
