package com.opensourceteam.modules.admin.business.system.manager.user.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensourceteam.modules.admin.business.system.manager.organization.service.SystemOrganizationService;
import com.opensourceteam.modules.admin.business.system.manager.user.vo.SystemUserVo;
import com.opensourceteam.modules.common.core.util.encrypt.md5.MD5Util;
import com.opensourceteam.modules.common.core.util.id.IdUtils;
import com.opensourceteam.modules.common.core.vo.message.ResultBack;
import com.opensourceteam.modules.dao.admin.SystemUserMapper;
import com.opensourceteam.modules.enume.BusinessTypeEnume;
import com.opensourceteam.modules.enume.IconTypeEnume;
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
 * 日期:  2018/1/29.
 * 功能描述:
 */
@Service
public class SystemUserService {

    @Autowired
    SystemOrganizationService organizationService;
    @Autowired
    SystemUserMapper systemUserMapper;

    public JSONArray getAllList(){
        JSONArray jsonArray = new JSONArray();
        JSONArray list = organizationService.getList();
        jsonArray.addAll(list);
        jsonArray.addAll(getList());
        return jsonArray;
    }


    public ResultBack editViewJSON(SystemUser vo){
        if( vo !=null && vo.getId() !=null){
            SystemUser po = systemUserMapper.selectByPrimaryKey(vo.getId());
            return new ResultBack(true,po);
        }
        return new ResultBack(false,"");
    }



    public ResultBack editJSON(SystemUser vo){

        SystemUser po = new SystemUser();
        if(vo !=null ){
            if(vo.getId() == null){
                //插入
                BeanUtils.copyProperties(vo,po);
                po.setCreateDate(new Date());
                po.setIsDel(false);
                po.setCreator(1);
                po.setTypeCode("1");
                po.setStatusCode("1");
                po.setLoginPwd(MD5Util.encryptMd5(vo.getLoginId(),vo.getLoginPwd()));

                systemUserMapper.insert(po);

                SystemUser parentPo = systemUserMapper.selectByPrimaryKey(vo.getParentId());
                if(parentPo !=null && org.apache.commons.lang3.StringUtils.isNotEmpty(parentPo.getParentIds())){
                    String parentIds = parentPo.getParentIds()  + po.getId()  +"/";
                    po.setParentIds(parentIds);
                    if(parentPo.getLevelNum() != null){
                        po.setLevelNum(parentPo.getLevelNum() + 1);
                    }
                    systemUserMapper.updateByPrimaryKey(po);
                }
            }else{
                //更新
                po = systemUserMapper.selectByPrimaryKey(vo.getId());
                if(po != null){
                    po.setName(vo.getName());
                    po.setRemark(vo.getRemark());
                    po.setLoginId(vo.getLoginId());
                    if(StringUtils.isNotEmpty(vo.getLoginPwd())){
                        po.setLoginPwd(MD5Util.encryptMd5(vo.getLoginId(),vo.getLoginPwd()));
                    }

                    if(po.getParentId() !=null && vo.getParentId() !=null && vo.getParentId().intValue() != po.getParentId().intValue()){
                        SystemUser parentPo = systemUserMapper.selectByPrimaryKey(vo.getParentId());
                        String parentIds = parentPo.getParentIds()  + po.getId()  +"/";
                        po.setParentIds(parentIds);
                        if(parentPo.getLevelNum() != null){
                            po.setLevelNum(parentPo.getLevelNum() + 1);
                        }
                    }
                    systemUserMapper.updateByPrimaryKey(po);
                }


            }

        }


        return new ResultBack(true,po);
    }

    public ResultBack editJSONDealIcon(SystemUser vo){
        ResultBack resultBack = editJSON(vo);
        if(resultBack.getSuccess() && resultBack.getObject() !=null){
            if(resultBack.getObject() instanceof SystemUser ){
                SystemUser voNew = (SystemUser)resultBack.getObject();
                SystemUserVo resultVo = new SystemUserVo();
                BeanUtils.copyProperties(voNew,resultVo);
                resultVo.setIcon(IconTypeEnume.Employee.getCloseUrl() );
                resultVo.setIconOpen(IconTypeEnume.Employee.getOpenUrl() );
                resultVo.setIconClose(IconTypeEnume.Employee.getCloseUrl());
                return new ResultBack(true,resultVo);
            }
        }
        return new ResultBack(false,"");
    }


    public ResultBack deleteJSON(TSystemOrganization vo){
        if( vo !=null && vo.getId() !=null){
            systemUserMapper.deleteByPrimaryKey(vo);
            return new ResultBack(true,"");
        }
        return new ResultBack(false,"");
    }

    public JSONArray getList(){
        JSONArray jsonArray = new JSONArray();
        List<SystemUser> list = selectAll();
        if(list !=null && list.size() >0){
            for(SystemUser po : list){
                JSONObject jsonObject = new JSONObject();
                String id = IdUtils.getPrefixId(BusinessTypeEnume.User,po.getId());
                jsonObject.put("id", id);
                String pId = IdUtils.getPrefixId(BusinessTypeEnume.Organization,po.getOrgId());
                jsonObject.put("pId",pId);
                jsonObject.put("name",po.getName());
                jsonObject.put("icon", IconTypeEnume.Employee.getCloseUrl() );
                jsonObject.put("iconOpen", IconTypeEnume.Employee.getOpenUrl() );
                jsonObject.put("iconClose", IconTypeEnume.Employee.getCloseUrl());

                jsonArray.add(jsonObject);
            }
        }

        return jsonArray;
    }
    public List<SystemUser> selectAll(){
        Condition condition = new Condition(SystemUser.class);
        condition.createCriteria().andEqualTo("isDel",false);
        condition.setOrderByClause("name asc");
        return systemUserMapper.selectByExample(condition);
    }

    public SystemUser getUserById(Integer id){
        return systemUserMapper.selectByPrimaryKey(id);
    }

    public SystemUser getUserByLoginId(String loginId){
        SystemUser systemUser = new SystemUser();
        systemUser.setLoginId(loginId);
        return  systemUserMapper.selectOne(systemUser);
    }
}
