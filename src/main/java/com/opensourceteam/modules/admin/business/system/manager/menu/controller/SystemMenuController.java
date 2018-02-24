package com.opensourceteam.modules.admin.business.system.manager.menu.controller;

import com.alibaba.fastjson.JSONArray;
import com.opensourceteam.modules.admin.business.system.manager.menu.service.SystemMenuService;
import com.opensourceteam.modules.common.core.vo.message.ResultBack;
import com.opensourceteam.modules.po.admin.SystemMenu;
import com.opensourceteam.modules.po.admin.SystemUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * 日期:  2018/1/30.
 * 功能描述: 菜单
 */
@Controller
@RequestMapping("/common/admin/system_manager/menu")
public class SystemMenuController {

    Logger logger = LoggerFactory.getLogger(SystemMenuController.class);

    @Autowired
    SystemMenuService systemMenuService;

    /**
     * 视图页
     * @return
     */
    @RequiresPermissions({"admin:systemmanager:menu:listView"})
    @RequestMapping("/listView")
    ModelAndView listView() {
        ModelAndView modelAndView = new ModelAndView("/common/admin/system_manager/menu/menuList");
        return modelAndView;
    }

    /**
     * 列表JSON
     * @return
     */
    @RequiresPermissions({"admin:systemmanager:menu:jsonList"})
    @RequestMapping("/jsonList")
    @ResponseBody
    String jsonList() {
        ResultBack resultBack = new ResultBack(true,systemMenuService.getList());
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
    Object editViewJSON(SystemMenu vo) {
        ResultBack resultBack = systemMenuService.editViewJSON(vo);
        return resultBack;
    }
    /**
     * 编辑操作
     * @param vo
     * @return
     */
    @RequestMapping("/editJSON")
    @ResponseBody
    Object editJSON(SystemMenu vo) {
        ResultBack resultBack = systemMenuService.editJSONDealIcon(vo);
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
        ResultBack resultBack = systemMenuService.deleteJSON(id);
        return resultBack;
    }

    /**
     * 删除操作
     * @param ids
     * @return
     */
    @RequestMapping("/deleteIdsJSON")
    @ResponseBody
    Object deleteIdsJSON(String ids) {
        ResultBack resultBack = systemMenuService.deleteIdsJSON(ids);
        return resultBack;
    }

    /**
     * 编辑页面
     * @param vo
     * @return
     */
    @RequestMapping("/getListSimple")
    @ResponseBody
    Object getListSimple() {
        ResultBack resultBack = new ResultBack(true,systemMenuService.getListSimple());
        return resultBack;
    }
}
