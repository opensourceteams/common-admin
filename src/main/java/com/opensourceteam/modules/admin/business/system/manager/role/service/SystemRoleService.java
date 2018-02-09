package com.opensourceteam.modules.admin.business.system.manager.role.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensourceteam.modules.admin.base.service.BaseService;
import com.opensourceteam.modules.admin.business.system.manager.menu.service.SystemMenuService;
import com.opensourceteam.modules.admin.business.system.manager.organization.service.SystemOrganizationService;
import com.opensourceteam.modules.admin.business.system.manager.role.vo.SystemRoleVo;

import com.opensourceteam.modules.common.core.util.id.IdUtils;
import com.opensourceteam.modules.common.core.vo.message.ResultBack;
import com.opensourceteam.modules.dao.admin.SystemRoleMapper;
import com.opensourceteam.modules.dao.admin.SystemRolePermissionMapper;
import com.opensourceteam.modules.enume.BusinessTypeEnume;
import com.opensourceteam.modules.enume.IconTypeEnume;
import com.opensourceteam.modules.po.admin.SystemRole;
import com.opensourceteam.modules.po.admin.SystemRolePermission;
import com.opensourceteam.modules.po.admin.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.List;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/2/7.
 * 功能描述:
 */
@Service
public class SystemRoleService extends BaseService {

    @Autowired
    SystemOrganizationService organizationService;

    @Autowired
    SystemRoleMapper systemRoleMapper;

    @Autowired
    SystemMenuService systemMenuService;

    @Autowired
    SystemRolePermissionMapper systemRolePermissionMapper;


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
                String sId = IdUtils.getPrefixId(BusinessTypeEnume.Role,po.getId());
                jsonObject.put("sId",sId);
                jsonObject.put("id",sId);
                jsonObject.put("pId",IdUtils.getPrefixId(BusinessTypeEnume.Organization,po.getOrgId()));
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

    public ResultBack editJSON(SystemRoleVo vo){

        SystemRole po = new SystemRole();
        if(vo !=null ){
            if(vo.getId() == null){
                //插入
                BeanUtils.copyProperties(vo,po);
                po.setCreateDate(new Date());
                po.setUpdateDate(new Date());
                po.setIsDel(false);
                po.setCreator(getCurrentUserId());
                po.setUpdator(getCurrentUserId());

                systemRoleMapper.insert(po);
                if(po !=null && po.getId() !=null){
                    vo.setId(po.getId());
                }


            }else{
                //更新
                po = systemRoleMapper.selectByPrimaryKey(vo.getId());
                if(po != null){
                    po.setRoleName(vo.getRoleName());
                    po.setRoleCode(vo.getRoleCode());
                    po.setRemark(vo.getRemark());
                    po.setUpdateDate(new Date());
                    po.setUpdator(getCurrentUserId());
                    systemRoleMapper.updateByPrimaryKey(po);
                    vo.setId(po.getId());
                }


            }

            deleteRolePermission(po.getId());
            insertRolePermissionRelation(vo);

        }


        return new ResultBack(true,po);
    }

    public ResultBack editJSONDealIcon(SystemRoleVo vo){
        ResultBack resultBack = editJSON(vo);
        if(resultBack.getSuccess() && resultBack.getObject() !=null){
            if(resultBack.getObject() instanceof SystemRole ){
                SystemRole voNew = (SystemRole)resultBack.getObject();
                SystemRoleVo resultVo = new SystemRoleVo();
                BeanUtils.copyProperties(voNew,resultVo);
                resultVo.setsPid(IdUtils.getPrefixId(BusinessTypeEnume.Organization,resultVo.getOrgId() ));
                resultVo.setId(voNew.getId());
                resultVo.setsId(IdUtils.getPrefixId(BusinessTypeEnume.Role,voNew.getId()));
                resultVo.setIcon(IconTypeEnume.Role.getCloseUrl() );
                resultVo.setIconOpen(IconTypeEnume.Role.getOpenUrl() );
                resultVo.setIconClose(IconTypeEnume.Role.getCloseUrl());
                return new ResultBack(true,resultVo);
            }
        }
        return new ResultBack(false,"");
    }

    public Boolean insertRolePermissionRelation(SystemRoleVo vo){
        if( vo !=null && vo.getId() !=null && vo.getPermissionList() !=null && vo.getPermissionList().size() >0){

            for(String permissionId : vo.getPermissionList()){
                SystemRolePermission systemRolePermission = new SystemRolePermission();
                systemRolePermission.setRoleId(vo.getId());
                systemRolePermission.setPermissionId(permissionId);
                systemRolePermission.setCreateDate(new Date());
                systemRolePermission.setUpdateDate(new Date());
                systemRolePermission.setCreator(getCurrentUserId());
                systemRolePermission.setUpdator(getCurrentUserId());
                systemRolePermission.setIsDel(false);

                systemRolePermissionMapper.insert(systemRolePermission);
            }

        }

        return true;
    }

    public ResultBack editViewJSON(Integer id){
        if( id !=null){
            SystemRole po = systemRoleMapper.selectByPrimaryKey(id);
            SystemRoleVo systemRoleVo = new SystemRoleVo();
            BeanUtils.copyProperties(po,systemRoleVo);
            return new ResultBack(true,systemRoleVo);
        }
        return new ResultBack(false,"");
    }

    public Boolean deleteRolePermission(Integer roleId){
        Condition condition = new Condition(SystemRolePermission.class);
        condition.createCriteria().andEqualTo("roleId",roleId);
        systemRolePermissionMapper.deleteByExample(condition);
        return true;
    }

    public ResultBack deleteJSON(Integer id){
        if( id !=null){
            deleteRolePermission(id);
            systemRoleMapper.deleteByPrimaryKey(id);
            return new ResultBack(true,"");
        }
        return new ResultBack(false,"");
    }

}
