package com.opensourceteam.modules.admin.business.system.manager.role.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensourceteam.modules.admin.business.system.manager.organization.service.SystemOrganizationService;
import com.opensourceteam.modules.common.core.util.id.IdUtils;
import com.opensourceteam.modules.dao.admin.SystemRoleMapper;
import com.opensourceteam.modules.enume.BusinessTypeEnume;
import com.opensourceteam.modules.enume.IconTypeEnume;
import com.opensourceteam.modules.po.admin.SystemRole;
import com.opensourceteam.modules.po.admin.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/2/7.
 * 功能描述:
 */
@Service
public class SystemRoleService {

    @Autowired
    SystemOrganizationService organizationService;

    @Autowired
    SystemRoleMapper systemRoleMapper;

    public JSONArray getAllList(){
        JSONArray jsonArray = new JSONArray();
        JSONArray list = organizationService.getList();
        jsonArray.addAll(list);
        jsonArray.addAll(getList());
        return jsonArray;
    }

    public JSONArray getList(){
        JSONArray jsonArray = new JSONArray();
        List<SystemRole> list = selectAll();
        if(list !=null && list.size() >0){
            for(SystemRole po : list){
                JSONObject jsonObject = new JSONObject();
                String id = IdUtils.getPrefixId(BusinessTypeEnume.Role,po.getId());
                jsonObject.put("id",id);
                jsonObject.put("pId",po.getOrgId());
                jsonObject.put("name",po.getRoleName());
                jsonObject.put("icon", IconTypeEnume.Role.getCloseUrl() );
                jsonObject.put("iconOpen", IconTypeEnume.Role.getOpenUrl() );
                jsonObject.put("iconClose", IconTypeEnume.Role.getCloseUrl());
                jsonArray.add(jsonObject);
            }
        }

        return jsonArray;
    }

    public List<SystemRole> selectAll(){
        Condition condition = new Condition(SystemUser.class);
        condition.createCriteria().andEqualTo("isDel",false);
        condition.setOrderByClause("role_name asc");
        return systemRoleMapper.selectByExample(condition);
    }
}
