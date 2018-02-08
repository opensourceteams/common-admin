package com.opensourceteam.modules.admin.business.system.manager.permission.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensourceteam.modules.admin.base.service.BaseService;
import com.opensourceteam.modules.admin.business.system.manager.menu.service.SystemMenuService;
import com.opensourceteam.modules.admin.business.system.manager.permission.vo.SystemPermissionVo;
import com.opensourceteam.modules.admin.business.system.manager.user.vo.SystemUserVo;
import com.opensourceteam.modules.common.core.util.id.IdUtils;
import com.opensourceteam.modules.common.core.vo.message.ResultBack;
import com.opensourceteam.modules.dao.admin.SystemPermissionMapper;
import com.opensourceteam.modules.enume.BusinessTypeEnume;
import com.opensourceteam.modules.enume.IconTypeEnume;
import com.opensourceteam.modules.po.admin.SystemPermission;
import com.opensourceteam.modules.po.admin.SystemUser;
import com.opensourceteam.modules.po.admin.TSystemOrganization;
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
 * 日期:  2018/2/8.
 * 功能描述:
 */
@Service
public class PermissionService extends BaseService{

    @Autowired
    SystemPermissionMapper systemPermissionMapper;

    @Autowired
    SystemMenuService systemMenuService;

    public JSONArray getAllList(){
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll( systemMenuService.getList());
        jsonArray.addAll(getList());
        return jsonArray;
    }

    public JSONArray getList(){
        JSONArray jsonArray = new JSONArray();
        List<SystemPermission> list = selectAll();
        if(list !=null && list.size() >0){
            for(SystemPermission po : list){
                JSONObject jsonObject = new JSONObject();
                String id = IdUtils.getPrefixId(BusinessTypeEnume.Permission,po.getId());
                jsonObject.put("id", id);
                jsonObject.put("pId",po.getMenuId());
                jsonObject.put("name",po.getPermissionName());
                jsonObject.put("icon", IconTypeEnume.Permision.getCloseUrl() );
                jsonObject.put("iconOpen", IconTypeEnume.Permision.getOpenUrl() );
                jsonObject.put("iconClose", IconTypeEnume.Permision.getCloseUrl());

                jsonArray.add(jsonObject);
            }
        }

        return jsonArray;
    }
    public List<SystemPermission> selectAll(){
        Condition condition = new Condition(SystemUser.class);
        condition.createCriteria().andEqualTo("isDel",false);
        condition.setOrderByClause("permission_name asc");
        return systemPermissionMapper.selectByExample(condition);
    }

    public ResultBack editViewJSON(SystemPermission vo){
        if( vo !=null && vo.getId() !=null){
            SystemPermission po = systemPermissionMapper.selectByPrimaryKey(vo.getId());
            return new ResultBack(true,po);
        }
        return new ResultBack(false,"");
    }

    public ResultBack editJSON(SystemPermission vo){

        SystemPermission po = new SystemPermission();
        Integer LoginId = getCurrentUserId();
        if(vo !=null ){
            if(vo.getId() == null){
                //插入
                BeanUtils.copyProperties(vo,po);
                po.setCreateDate(new Date());
                po.setUpdateDate(new Date());
                po.setIsDel(false);
                po.setCreator(LoginId);
                po.setUpdator(LoginId);

                systemPermissionMapper.insert(po);

            }else{
                //更新
                po = systemPermissionMapper.selectByPrimaryKey(vo.getId());
                if(po != null){
                    //插入
                    //BeanUtils.copyProperties(vo,po);
                    po.setPermissionCode(vo.getPermissionCode());
                    po.setPermissionName(vo.getPermissionName());
                    po.setUpdateDate(new Date());
                    po.setUpdator(getCurrentUserId());
                    systemPermissionMapper.updateByPrimaryKey(po);
                }


            }

        }


        return new ResultBack(true,po);
    }

    public ResultBack editJSONDealIcon(SystemPermission vo){
        ResultBack resultBack = editJSON(vo);
        if(resultBack.getSuccess() && resultBack.getObject() !=null){
            if(resultBack.getObject() instanceof SystemPermission ){
                SystemPermission voNew = (SystemPermission)resultBack.getObject();
                SystemPermissionVo resultVo = new SystemPermissionVo();
                BeanUtils.copyProperties(voNew,resultVo);
                String id = IdUtils.getPrefixId(BusinessTypeEnume.Permission,voNew.getId());
                resultVo.setsId(id);
                resultVo.setIcon(IconTypeEnume.Permision.getCloseUrl() );
                resultVo.setIconOpen(IconTypeEnume.Permision.getOpenUrl() );
                resultVo.setIconClose(IconTypeEnume.Permision.getCloseUrl());
                return new ResultBack(true,resultVo);
            }
        }
        return new ResultBack(false,"");
    }

    public ResultBack deleteJSON(Integer id){
        if( id !=null){
            systemPermissionMapper.deleteByPrimaryKey(id);
            return new ResultBack(true,"");
        }
        return new ResultBack(false,"");
    }

}
