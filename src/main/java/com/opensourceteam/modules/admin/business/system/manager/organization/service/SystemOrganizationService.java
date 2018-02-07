package com.opensourceteam.modules.admin.business.system.manager.organization.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensourceteam.modules.admin.base.service.BaseService;
import com.opensourceteam.modules.admin.business.system.manager.organization.vo.OrganizationVo;
import com.opensourceteam.modules.common.core.vo.message.ResultBack;
import com.opensourceteam.modules.dao.admin.TSystemOrganizationMapper;
import com.opensourceteam.modules.enume.IconTypeEnume;
import com.opensourceteam.modules.enume.RootNodeEnume;
import com.opensourceteam.modules.po.admin.TSystemOrganization;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/1/28.
 * 功能描述:
 */
@Service
public class SystemOrganizationService extends BaseService{

    @Autowired
    TSystemOrganizationMapper tSystemOrganizationMapper;

    public JSONArray getList(){
        JSONArray jsonArray = new JSONArray();
        List<TSystemOrganization> list = getAllOrganization();
        if(list !=null && list.size() >0){
            for(TSystemOrganization po : list){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",po.getId());
                jsonObject.put("pId",po.getParentId());
                jsonObject.put("name",po.getName());
                if((IconTypeEnume.Organization.getValue()+"").equals(po.getOrgType())){
                    jsonObject.put("icon", IconTypeEnume.Organization.getCloseUrl() );
                    jsonObject.put("iconOpen", IconTypeEnume.Organization.getOpenUrl() );
                    jsonObject.put("iconClose", IconTypeEnume.Organization.getCloseUrl());
                }else if((IconTypeEnume.Department.getValue()+"").equals(po.getOrgType())){
                    jsonObject.put("icon", IconTypeEnume.Department.getCloseUrl() );
                    jsonObject.put("iconOpen", IconTypeEnume.Department.getOpenUrl() );
                    jsonObject.put("iconClose", IconTypeEnume.Department.getCloseUrl());
                }else if((IconTypeEnume.Group.getValue()+"").equals(po.getOrgType())){
                    jsonObject.put("icon", IconTypeEnume.Group.getCloseUrl() );
                    jsonObject.put("iconOpen", IconTypeEnume.Group.getOpenUrl() );
                    jsonObject.put("iconClose", IconTypeEnume.Group.getCloseUrl());
                }
                jsonObject.put("open",true);
                jsonArray.add(jsonObject);
            }
        }


        return jsonArray;
    }

    public ResultBack editViewJSON(TSystemOrganization vo){
        if( vo !=null && vo.getId() !=null){
            TSystemOrganization po = tSystemOrganizationMapper.selectByPrimaryKey(vo.getId());
            return new ResultBack(true,po);
        }
        return new ResultBack(false,"");
    }

    public ResultBack deleteJSON(TSystemOrganization vo){
        if( vo !=null && vo.getId() !=null){
            tSystemOrganizationMapper.deleteByPrimaryKey(vo);
            return new ResultBack(true,"");
        }
        return new ResultBack(false,"");
    }
    public ResultBack editJSONOrganization(TSystemOrganization vo){

        TSystemOrganization po = new TSystemOrganization();
        if(vo !=null ){
            if(vo.getId() == null){
                //插入
                BeanUtils.copyProperties(vo,po);
                po.setCreateDate(new Date());
                po.setIsDel(false);
                po.setCreator(getCurrentUserId());
                if(vo.getParentId() == null){
                    //根节点
                    po.setParentId(RootNodeEnume.RootNodeParent.getValue());
                    po.setLevelNum(1);
                }
                tSystemOrganizationMapper.insert(po);
                if(vo.getParentId() == null){
                    String parentIds = "/" +RootNodeEnume.RootNodeParent.getValue() +"/"  + po.getId()  +"/";
                    po.setParentIds(parentIds);
                    tSystemOrganizationMapper.updateByPrimaryKey(po);
                }else{
                    TSystemOrganization parentPo = tSystemOrganizationMapper.selectByPrimaryKey(vo.getParentId());
                    if(parentPo !=null && org.apache.commons.lang3.StringUtils.isNotEmpty(parentPo.getParentIds())){
                        String parentIds = parentPo.getParentIds()  + po.getId()  +"/";
                        po.setParentIds(parentIds);
                        if(parentPo.getLevelNum() !=null ){
                            po.setLevelNum( parentPo.getLevelNum() + 1);
                        }
                        tSystemOrganizationMapper.updateByPrimaryKey(po);
                    }
                }



            }else{
                //更新
                po = tSystemOrganizationMapper.selectByPrimaryKey(vo.getId());
                if(po != null){
                    po.setName(vo.getName());
                    po.setOrgType(vo.getOrgType());
                    po.setRemark(vo.getRemark());

                    if( vo.getParentId() !=null){
                        po.setParentId(vo.getParentId());
                        TSystemOrganization parentPo = tSystemOrganizationMapper.selectByPrimaryKey(vo.getParentId());
                        if(parentPo == null){
                            String parentIds = "/" + RootNodeEnume.RootNodeParent.getValue() +"/"  + po.getId()  +"/";
                            po.setParentIds(parentIds);
                            po.setLevelNum(  1);
                        }else{
                            if( org.apache.commons.lang3.StringUtils.isNotEmpty(parentPo.getParentIds())){

                                String parentIds = parentPo.getParentIds()  + po.getId()  +"/";
                                po.setParentIds(parentIds);
                                if(parentPo.getLevelNum() !=null ){
                                    po.setLevelNum( parentPo.getLevelNum() + 1);
                                }
                            }

                        }


                    }
                    tSystemOrganizationMapper.updateByPrimaryKey(po);
                }


            }

        }


        return new ResultBack(true,po);
    }

    public ResultBack editJSON(TSystemOrganization vo){
        ResultBack resultBack = editJSONOrganization(vo);
        if(resultBack.getSuccess() && resultBack.getObject() !=null){
                if(resultBack.getObject() instanceof TSystemOrganization ){
                    TSystemOrganization voNew = (TSystemOrganization)resultBack.getObject();
                    OrganizationVo organizationVo = new OrganizationVo();
                    BeanUtils.copyProperties(voNew,organizationVo);
                    if((IconTypeEnume.Organization.getValue()+"").equals(organizationVo.getOrgType())){
                        organizationVo.setIcon(IconTypeEnume.Organization.getCloseUrl() );
                        organizationVo.setIconOpen(IconTypeEnume.Organization.getOpenUrl() );
                        organizationVo.setIconClose(IconTypeEnume.Organization.getCloseUrl());
                    }else if((IconTypeEnume.Department.getValue()+"").equals(organizationVo.getOrgType())){
                        organizationVo.setIcon(IconTypeEnume.Department.getCloseUrl() );
                        organizationVo.setIconOpen(IconTypeEnume.Department.getOpenUrl() );
                        organizationVo.setIconClose(IconTypeEnume.Department.getCloseUrl());
                    }else if((IconTypeEnume.Group.getValue()+"").equals(organizationVo.getOrgType())){
                        organizationVo.setIcon(IconTypeEnume.Group.getCloseUrl() );
                        organizationVo.setIconOpen(IconTypeEnume.Group.getOpenUrl() );
                        organizationVo.setIconClose(IconTypeEnume.Group.getCloseUrl());
                    }
                    return new ResultBack(true,organizationVo);
                }
        }
        return new ResultBack(false,"");
    }

    public List<TSystemOrganization> getAllOrganization(){
        Condition condition = new Condition(TSystemOrganization.class);
        condition.createCriteria().andEqualTo("isDel",false)

        ;
        condition.setOrderByClause("id asc");
        List<TSystemOrganization> list = tSystemOrganizationMapper.selectByExample(condition);
        return list;
    }

    public ResultBack deleteJSON(Integer key){
        if(key !=null ){
            tSystemOrganizationMapper.deleteByPrimaryKey(key);
            return new ResultBack(true,"");
        }

        return new ResultBack(false,"");
    }

    public ResultBack deleteIdsJSON(String ids){
        if(StringUtils.isNotEmpty(ids) ){
            String[] idArray = ids.split(",");
            List<Integer> idList = new ArrayList<>();
            for(String id : idArray){
                if(StringUtils.isNotEmpty(id) ){
                    idList.add(Integer.parseInt(id));
                }

            }
            Condition condition = new Condition(TSystemOrganization.class);
            condition.createCriteria().andIn("id",idList);
            tSystemOrganizationMapper.deleteByExample(condition);
            return new ResultBack(true,"");
        }

        return new ResultBack(false,"");
    }
}
