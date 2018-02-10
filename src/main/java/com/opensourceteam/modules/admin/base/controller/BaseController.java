package com.opensourceteam.modules.admin.base.controller;

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
}
