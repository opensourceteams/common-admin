package com.opensourceteam.modules.admin.configuration.spring.exception.handler;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 开发人:刘文 2
 * QQ: 372065525
 * 日期:  2018/2/10.
 * 功能描述:
 */
@ControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(CustomerExceptionHandler.class);

    /**
     * 没有权限访问
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
   // @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handleException(AuthorizationException e) {
        ModelAndView modelAndView = new ModelAndView("/modules/common/transfer/transfer");
        modelAndView.addObject("url","/module/common/message/show");
        modelAndView.addObject("message","没有权限访问");
        logger.debug("[CustomerExceptionHandler] 无权限访问处理");
        return modelAndView;
    }

    /**
     * 用户不存在　
     * @param e
     * @return
     */
    @ExceptionHandler(UnknownAccountException.class)
    public ModelAndView handleExceptionUnknownAccountException(UnknownAccountException e) {
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("message","用户不存在");
        modelAndView.addObject("code","1");
        logger.debug("[CustomerExceptionHandler] 用户不存在");
        return modelAndView;
    }

    /**
     * 密码错误　
     * @param e
     * @return
     */
    @ExceptionHandler(IncorrectCredentialsException.class)
    public ModelAndView handleExceptionIncorrectCredentialsException(IncorrectCredentialsException e) {
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("code","2");
        modelAndView.addObject("message","密码错误");
        logger.debug("[CustomerExceptionHandler] 密码错误");
        return modelAndView;
    }




}
