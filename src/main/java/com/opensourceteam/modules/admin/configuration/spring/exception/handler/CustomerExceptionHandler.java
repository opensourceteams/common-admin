package com.opensourceteam.modules.admin.configuration.spring.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 开发人:刘文 2
 * QQ: 372065525
 * 日期:  2018/2/10.
 * 功能描述:
 */
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(CustomerExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        //return super.handleExceptionInternal(ex, body, headers, status, request);
        logger.debug("[CustomerExceptionHandler]");
       // return new ResponseEntity(body, headers, status);
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
