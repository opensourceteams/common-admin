package com.opensourceteam.modules.admin.business.common.message.controller;

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
@RequestMapping("/module/common/message")
public class MessageController {

    @RequestMapping("/show")
    public ModelAndView show(String message) {
        ModelAndView modelAndView = new ModelAndView("/modules/common/message/message");
        modelAndView.addObject("message",message) ;
        return modelAndView;
    }
}
