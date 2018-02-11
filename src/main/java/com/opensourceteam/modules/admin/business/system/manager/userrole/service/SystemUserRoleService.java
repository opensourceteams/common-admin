package com.opensourceteam.modules.admin.business.system.manager.userrole.service;

import com.opensourceteam.modules.admin.base.service.BaseService;
import com.opensourceteam.modules.common.core.util.id.IdUtils;
import com.opensourceteam.modules.dao.admin.SystemUserRoleMapper;
import com.opensourceteam.modules.enume.BusinessTypeEnume;
import com.opensourceteam.modules.po.admin.SystemUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/2/11.
 * 功能描述:
 */
@Service
public class SystemUserRoleService extends BaseService {

    @Autowired
    SystemUserRoleMapper systemUserRoleMapper;

    /**
     * 通过用户id,查找用户有哪些角色
     * @param userId
     * @return
     */
    public List<SystemUserRole> getSystemUserRoleListByUserId(Integer userId){
        SystemUserRole systemUserRole = new SystemUserRole();
        systemUserRole.setUserId(userId);
        return systemUserRoleMapper.select(systemUserRole);
    }

    /**
     * 通过用户id,查找用户有哪些角色 id 集合(去掉前缀)
     * @param userId
     * @return
     */
    public List<Integer> getRoleIdList(Integer userId){
        List<Integer> list = new ArrayList<>();
        List<SystemUserRole> systemUserRoleList = getSystemUserRoleListByUserId(userId);
        for(SystemUserRole systemUserRole : systemUserRoleList){
            list.add(IdUtils.getRemovePrefixId(BusinessTypeEnume.Role.getPrefix(),systemUserRole.getRoleId()));
        }
        return list;
    }
}
