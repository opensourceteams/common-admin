package com.opensourceteam.modules.admin.configuration.spring.exception.handler;

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

    @ExceptionHandler(AuthorizationException.class)
   // @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handleException(AuthorizationException e) {
        ModelAndView modelAndView = new ModelAndView("/modules/common/transfer/transfer");
        modelAndView.addObject("url","/module/common/message/show");
        modelAndView.addObject("message","没有权限访问");
        logger.debug("[CustomerExceptionHandler] 无权限访问处理");
        return modelAndView;
    }
}
