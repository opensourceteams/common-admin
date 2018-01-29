package com.opensourceteam.modules.admin.business.system.manager.user.controller;

import com.alibaba.fastjson.JSONArray;
import com.opensourceteam.modules.admin.business.system.manager.user.service.SystemUserService;
import com.opensourceteam.modules.common.core.vo.message.ResultBack;
import com.opensourceteam.modules.po.admin.SystemUser;
import com.opensourceteam.modules.po.admin.TSystemOrganization;
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
 * 日期:  2018/1/26.
 * 功能描述:
 */
@Controller
@RequestMapping("/common/admin/system_manager/user")
public class SystemUserController {

    Logger logger = LoggerFactory.getLogger(SystemUserController.class);


    @Autowired
    SystemUserService systemUserService;

    @RequestMapping("/index")
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("common/admin/system_manager/user/userIndex");
        logger.info("[OrganizationController index]");
        return modelAndView;
    }

    /**
     * 视图页
     * @return
     */
    @RequestMapping("/listView")
    ModelAndView listView() {
        ModelAndView modelAndView = new ModelAndView("common/admin/system_manager/user/systemUserList");
        /**
         * 上级
         */
        modelAndView.addObject("superiors",systemUserService.selectAll());
        return modelAndView;
    }

    /**
     * 列表JSON
     * @return
     */
    @RequestMapping("/jsonList")
    @ResponseBody
    String jsonList() {
        ResultBack resultBack = new ResultBack(true, systemUserService.getAllList());
        logger.info("[OrganizationController jsonList]");
        return JSONArray.toJSONString(resultBack);
    }

    /**
     * 编辑页面
     * @param vo
     * @return
     */
    @RequestMapping("/editViewJSON")
    @ResponseBody
    Object editViewJSON(SystemUser vo) {
        ResultBack resultBack = systemUserService.editViewJSON(vo);
        return resultBack;
    }
    /**
     * 编辑操作
     * @param vo
     * @return
     */
    @RequestMapping("/editJSON")
    @ResponseBody
    Object editJSON(SystemUser vo) {
        ResultBack resultBack = systemUserService.editJSONDealIcon(vo);
        return resultBack;
    }



    /**
     * 删除操作
     * @param vo
     * @return
     */
    @RequestMapping("/deleteJSON")
    @ResponseBody
    Object deleteJSON(TSystemOrganization vo) {
        ResultBack resultBack = systemUserService.deleteJSON(vo);
        return resultBack;
    }
}
