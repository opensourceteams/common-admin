package com.opensourceteam.modules.admin.business.system.manager.organization.controller;

import com.alibaba.fastjson.JSONArray;
import com.opensourceteam.modules.admin.business.system.manager.organization.service.SystemOrganizationService;
import com.opensourceteam.modules.common.core.vo.message.ResultBack;
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
 * 功能描述: 机构
 */
@Controller
@RequestMapping("/common/admin/system_manager/organization")
public class SystemOrganizationController {

    Logger logger = LoggerFactory.getLogger(SystemOrganizationController.class);


    @Autowired
    SystemOrganizationService organizationService;



    /**
     * 视图页
     * @return
     */
    @RequestMapping("/listView")
    ModelAndView listView() {
        ModelAndView modelAndView = new ModelAndView("common/admin/system_manager/organization/organizationList");
        return modelAndView;
    }

    /**
     * 列表JSON
     * @return
     */
    @RequestMapping("/jsonList")
    @ResponseBody
    String jsonList() {
        ResultBack resultBack = new ResultBack(true,organizationService.getList());
        logger.info("[OrganizationController jsonList]");
        return JSONArray.toJSONString(resultBack);
    }

    /**
     * 编辑操作
     * @param vo
     * @return
     */
    @RequestMapping("/editJSON")
    @ResponseBody
    Object editJSON(TSystemOrganization vo) {
        ResultBack resultBack = organizationService.editJSON(vo);

        logger.info("[OrganizationController jsonList]");
        return resultBack;
    }

    /**
     * 编辑页面
     * @param vo
     * @return
     */
    @RequestMapping("/editViewJSON")
    @ResponseBody
    Object editViewJSON(TSystemOrganization vo) {
        ResultBack resultBack = organizationService.editViewJSON(vo);
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
        ResultBack resultBack = organizationService.deleteJSON(vo);
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
        ResultBack resultBack = organizationService.deleteIdsJSON(ids);
        return resultBack;
    }
}
