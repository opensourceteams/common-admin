package com.opensourceteam.modules.admin.business.system.manager.organization.controller;

import com.alibaba.fastjson.JSONArray;
import com.opensourceteam.modules.admin.business.system.manager.organization.service.OrganizationService;
import com.opensourceteam.modules.common.core.vo.message.ResultBack;
import com.opensourceteam.modules.po.admin.TSystemOrganization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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


    @Autowired
    OrganizationService organizationService;

    @RequestMapping("/index")
    ModelAndView index(String message) {
        ModelAndView modelAndView = new ModelAndView("common/admin/system_manager/organization/organizationIndex");
        logger.info("[OrganizationController index]");
        return modelAndView;
    }

    @RequestMapping("/list")
    ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("common/admin/system_manager/organization/organizationList");

        logger.info("[OrganizationController index]");
        return modelAndView;
    }

    @RequestMapping("/jsonList")
    @ResponseBody
    String jsonList() {
        ResultBack resultBack = new ResultBack(true,organizationService.getList());
        logger.info("[OrganizationController jsonList]");
        return JSONArray.toJSONString(resultBack);
    }

    @RequestMapping("/addJSONOrganization")
    @ResponseBody
    Object addJSONOrganization(TSystemOrganization vo) {
        ResultBack resultBack = organizationService.addJSONOrganization(vo);

        logger.info("[OrganizationController jsonList]");
        return resultBack;
    }
}
