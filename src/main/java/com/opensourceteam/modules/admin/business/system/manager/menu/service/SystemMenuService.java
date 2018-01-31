package com.opensourceteam.modules.admin.business.system.manager.menu.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensourceteam.modules.admin.base.service.BaseService;
import com.opensourceteam.modules.admin.business.system.manager.menu.vo.SystemMenuVo;
import com.opensourceteam.modules.common.core.vo.message.ResultBack;
import com.opensourceteam.modules.dao.admin.SystemMenuMapper;
import com.opensourceteam.modules.enume.OrgTypeEnume;
import com.opensourceteam.modules.enume.RootNodeEnume;
import com.opensourceteam.modules.po.admin.SystemMenu;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * 日期:  2018/1/30.
 * 功能描述:
 */
@Service
public class SystemMenuService extends BaseService{

    Logger logger = LoggerFactory.getLogger(SystemMenuService.class);

    @Autowired
    SystemMenuMapper systemMenuMapper;

    public JSONArray getList(){
        JSONArray jsonArray = new JSONArray();
        List<SystemMenu> list = getAll();
        if(list !=null && list.size() >0){
            for(SystemMenu po : list){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",po.getId());
                jsonObject.put("pId",po.getParentId());
                jsonObject.put("name",po.getMenuName());
                jsonObject.put("icon",OrgTypeEnume.Menu.getCloseUrl() );
                jsonObject.put("iconOpen",OrgTypeEnume.Menu.getOpenUrl() );
                jsonObject.put("iconClose",OrgTypeEnume.Menu.getCloseUrl());
                jsonObject.put("open",true);
                jsonArray.add(jsonObject);
            }
        }


        return jsonArray;
    }

    public ResultBack editJSON(SystemMenu vo){

        SystemMenu po = new SystemMenu();
        if(vo !=null ){
            if(vo.getId() == null){
                //插入
                Integer currentLoginId = getCurrentUserId();
                BeanUtils.copyProperties(vo,po);
                po.setCreateDate(new Date());
                po.setUpdateDate(new Date());
                po.setCreator(currentLoginId);
                po.setUpdator(currentLoginId);
                po.setIsDel(false);
                //po.setId(0);
                if(vo.getParentId() == null){
                    po.setParentId(RootNodeEnume.RootNodeParent.getValue());
                    po.setLevleNum(1);
                }

                logger.debug("[insert systemMenuMapper po]:{}",po);
                systemMenuMapper.insert(po);

                if(vo.getParentId() == null){
                    //根节点
                    String parentIds = "/0/"  + po.getId()  +"/";
                    po.setParentIds(parentIds);
                    systemMenuMapper.updateByPrimaryKey(po);
                }else{
                    SystemMenu parentPo = systemMenuMapper.selectByPrimaryKey(vo.getParentId());
                    if(parentPo !=null && org.apache.commons.lang3.StringUtils.isNotEmpty(parentPo.getParentIds())){
                        String parentIds = parentPo.getParentIds()  + po.getId()  +"/";
                        po.setParentIds(parentIds);
                        po.setLevleNum(parentPo.getLevleNum() +1);
                        systemMenuMapper.updateByPrimaryKey(po);
                    }
                }


            }else{
                //更新
                po = systemMenuMapper.selectByPrimaryKey(vo.getId());
                if(po != null){
                    po.setMenuName(vo.getMenuName());

                    po.setRemark(vo.getRemark());
                    systemMenuMapper.updateByPrimaryKey(po);
                }

            }

        }


        return new ResultBack(true,po);
    }

    public ResultBack editJSONDealIcon(SystemMenu vo){
        ResultBack resultBack = editJSON(vo);
        if(resultBack.getSuccess() && resultBack.getObject() !=null){
            if(resultBack.getObject() instanceof SystemMenu ){
                SystemMenu editVo = (SystemMenu)resultBack.getObject();
                SystemMenuVo resultVo = new SystemMenuVo();
                BeanUtils.copyProperties(editVo,resultVo);
                resultVo.setIcon(OrgTypeEnume.Menu.getCloseUrl() );
                resultVo.setIconOpen(OrgTypeEnume.Menu.getOpenUrl() );
                resultVo.setIconClose(OrgTypeEnume.Menu.getCloseUrl());
                return new ResultBack(true,resultVo);
            }
        }
        return new ResultBack(false,"");
    }

    public ResultBack editViewJSON(SystemMenu vo){
        if( vo !=null && vo.getId() !=null){
            SystemMenu po = systemMenuMapper.selectByPrimaryKey(vo.getId());
            return new ResultBack(true,po);
        }
        return new ResultBack(false,"");
    }

    public List<SystemMenu> getAll(){
        Condition condition = new Condition(SystemMenu.class);
        condition.createCriteria().andEqualTo("isDel",false)
        ;
        condition.setOrderByClause("menu_name asc");
        List<SystemMenu> list = systemMenuMapper.selectByExample(condition);
        return list;
    }

    public ResultBack deleteJSON(Integer key){
        if(key !=null ){
            systemMenuMapper.deleteByPrimaryKey(key);
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
            Condition condition = new Condition(SystemMenu.class);
            condition.createCriteria().andIn("id",idList);
            systemMenuMapper.deleteByExample(condition);
            return new ResultBack(true,"");
        }

        return new ResultBack(false,"");
    }
}
