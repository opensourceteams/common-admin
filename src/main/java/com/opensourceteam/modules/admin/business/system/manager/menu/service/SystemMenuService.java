package com.opensourceteam.modules.admin.business.system.manager.menu.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensourceteam.modules.admin.base.service.BaseService;
import com.opensourceteam.modules.admin.business.system.manager.menu.vo.SystemMenuVo;
import com.opensourceteam.modules.common.core.vo.message.ResultBack;
import com.opensourceteam.modules.dao.admin.SystemMenuMapper;
import com.opensourceteam.modules.enume.IconTypeEnume;
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
                jsonObject.put("icon", IconTypeEnume.Menu.getCloseUrl() );
                jsonObject.put("iconOpen", IconTypeEnume.Menu.getOpenUrl() );
                jsonObject.put("iconClose", IconTypeEnume.Menu.getCloseUrl());
                jsonObject.put("open",true);
                jsonArray.add(jsonObject);
            }
        }


        return jsonArray;
    }

    public List<SystemMenuVo> getListSystemMenuRelationAll(){
        List<SystemMenuVo> resultList = new ArrayList<>();
        List<SystemMenu> list = getAll();
        for(SystemMenu systemMenu : list){
            SystemMenuVo systemMenuVo = new SystemMenuVo();
            BeanUtils.copyProperties(systemMenu,systemMenuVo);
            dealMenuChildList(resultList,systemMenuVo);
        }
        return resultList;
    }

    public void dealMenuChildList(List<SystemMenuVo> list,SystemMenuVo systemMenuVo){
        if(systemMenuVo.getLevelNum().intValue() == RootNodeEnume.RootNodeParent.getValue().intValue() + 1){
           if( ! existeMenu(list,systemMenuVo)){
               //增加根节点
               list.add(systemMenuVo);
           }
        }else{
            for(SystemMenuVo vo : list){
                if(vo.getId().intValue() == systemMenuVo.getParentId()){
                    if( ! existeMenu(vo.getChildList(),systemMenuVo)){
                        //增加子节点
                        vo.getChildList().add(systemMenuVo);
                    }
                }else{
                    addMenuChild(vo.getChildList(),systemMenuVo);
                }

            }


        }
    }


    public Boolean existeMenu(List<SystemMenuVo> list,SystemMenuVo systemMenuVo){
        Boolean result = false;
        for(SystemMenuVo vo : list){
            if(vo.getId().intValue() == systemMenuVo.getId().intValue()){
                return true;
            }else{

                if(vo !=null && vo.getChildList() !=null && vo.getChildList().size() >0){
                    //有儿子
                    return result | existeMenu(vo.getChildList(),systemMenuVo);
                }
            }
        }
        return result;
    }

    public void addMenuChild(List<SystemMenuVo> list,SystemMenuVo systemMenuVo){
        for(SystemMenuVo vo : list){
            if(vo.getId().intValue() == systemMenuVo.getParentId().intValue()){
                if(!existeMenu(list,systemMenuVo)){
                    vo.getChildList().add(systemMenuVo);
                }
            }else{
                if(vo !=null && vo.getChildList() !=null && vo.getChildList().size() >0){
                    //有儿子
                    addMenuChild(vo.getChildList(),systemMenuVo);
                }
            }
        }
    }




    public JSONArray getListAll(){
        JSONArray jsonArray = new JSONArray();
        List<SystemMenu> list = getAll();
        if(list !=null && list.size() >0){
            for(SystemMenu po : list){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",po.getId());
                jsonObject.put("pId",po.getParentId());
                jsonObject.put("name",po.getMenuName());
                jsonObject.put("icon", IconTypeEnume.Menu.getCloseUrl() );
                jsonObject.put("iconOpen", IconTypeEnume.Menu.getOpenUrl() );
                jsonObject.put("iconClose", IconTypeEnume.Menu.getCloseUrl());
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
                    po.setLevelNum(1);
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
                        po.setLevelNum(parentPo.getLevelNum() +1);
                        systemMenuMapper.updateByPrimaryKey(po);
                    }
                }


            }else{
                //更新
                po = systemMenuMapper.selectByPrimaryKey(vo.getId());
                if(po != null){
                    po.setMenuName(vo.getMenuName());
                    po.setRemark(vo.getRemark());
                    po.setMenuUrl(vo.getMenuUrl());
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
                resultVo.setIcon(IconTypeEnume.Menu.getCloseUrl() );
                resultVo.setIconOpen(IconTypeEnume.Menu.getOpenUrl() );
                resultVo.setIconClose(IconTypeEnume.Menu.getCloseUrl());
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
        condition.setOrderByClause("level_num asc,menu_name asc");
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
