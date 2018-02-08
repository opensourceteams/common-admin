package com.opensourceteam.modules.admin.business.system.manager.permission.controller;

import com.alibaba.fastjson.JSONArray;
import com.opensourceteam.modules.admin.business.system.manager.permission.service.PermissionService;
import com.opensourceteam.modules.admin.business.system.manager.user.controller.SystemUserController;
import com.opensourceteam.modules.common.core.vo.message.ResultBack;
import com.opensourceteam.modules.po.admin.SystemPermission;
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
 * 日期:  2018/2/8.
 * 功能描述:
 */
@Controller
@RequestMapping("/common/admin/system_manager/permission")
public class PermissionController {

    Logger logger = LoggerFactory.getLogger(SystemUserController.class);

    @Autowired
    PermissionService permissionService;

    /**
     * 视图页
     * @return
     */
    @RequestMapping("/listView")
    ModelAndView listView() {
        ModelAndView modelAndView = new ModelAndView("common/admin/system_manager/permission/systemPermissionList");
        return modelAndView;
    }

    /**
     * 列表JSON
     * @return
     */
    @RequestMapping("/jsonList")
    @ResponseBody
    String jsonList() {
        ResultBack resultBack = new ResultBack(true, permissionService.getAllList());
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
    Object editViewJSON(SystemPermission vo) {
        ResultBack resultBack = permissionService.editViewJSON(vo);
        return resultBack;
    }
    /**
     * 编辑操作
     * @param vo
     * @return
     */
    @RequestMapping("/editJSON")
    @ResponseBody
    Object editJSON(SystemPermission vo) {
        ResultBack resultBack = permissionService.editJSONDealIcon(vo);
        return resultBack;
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @RequestMapping("/deleteJSON")
    @ResponseBody
    Object deleteJSON(Integer id) {
        ResultBack resultBack = permissionService.deleteJSON(id);
        return resultBack;
    }
}
