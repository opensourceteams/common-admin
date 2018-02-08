package com.opensourceteam.modules.admin.business.system.manager.role.controller;

import com.alibaba.fastjson.JSONArray;
import com.opensourceteam.modules.admin.business.system.manager.role.service.SystemRoleService;
import com.opensourceteam.modules.admin.business.system.manager.user.controller.SystemUserController;
import com.opensourceteam.modules.common.core.vo.message.ResultBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/2/7.
 * 功能描述:
 */
@Controller
@RequestMapping("/common/admin/system_manager/role")
public class SystemRoleController {

    Logger logger = LoggerFactory.getLogger(SystemRoleController.class);

    @Autowired
    SystemRoleService systemRoleService;


    /**
     * 视图页
     * @return
     */
    @RequestMapping("/listView")
    ModelAndView listView() {
        ModelAndView modelAndView = new ModelAndView("common/admin/system_manager/role/systemRoleList");
        return modelAndView;
    }

    /**
     * 列表JSON
     * @return
     */
    @RequestMapping("/jsonList")
    @ResponseBody
    String jsonList() {
        ResultBack resultBack = new ResultBack(true, systemRoleService.getAllList());
        logger.info("[OrganizationController jsonList]");
        return JSONArray.toJSONString(resultBack);
    }

}
