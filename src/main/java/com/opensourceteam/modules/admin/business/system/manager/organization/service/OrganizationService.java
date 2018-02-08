package com.opensourceteam.modules.admin.business.system.manager.organization.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensourceteam.modules.common.core.vo.message.ResultBack;
import com.opensourceteam.modules.dao.admin.TSystemOrganizationMapper;
import com.opensourceteam.modules.po.admin.TSystemOrganization;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.List;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/1/28.
 * 功能描述:
 */
@Service
public class OrganizationService {

    @Autowired
    TSystemOrganizationMapper tSystemOrganizationMapper;

    public JSONArray getList(){
        JSONArray jsonArray = new JSONArray();
        List<TSystemOrganization> list = getAllOrganization(0);
        if(list !=null && list.size() >0){
            for(TSystemOrganization po : list){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",po.getId());
                jsonObject.put("pId",po.getParentId());
                jsonObject.put("name",po.getName());
                jsonArray.add(jsonObject);
            }
        }


        return jsonArray;
    }

    public ResultBack editViewJSONOrganization(TSystemOrganization vo){
        if( vo !=null && vo.getId() !=null){
            TSystemOrganization po = tSystemOrganizationMapper.selectByPrimaryKey(vo.getId());
            return new ResultBack(true,po);
        }
        return new ResultBack(false,"");
    }

    public ResultBack deleteJSONOrganization(TSystemOrganization vo){
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
                po.setCreator(0);
                tSystemOrganizationMapper.insert(po);
            }else{
                //更新
                po = tSystemOrganizationMapper.selectByPrimaryKey(vo.getId());
                if(po != null){
                    po.setName(vo.getName());
                    po.setOrgType(vo.getOrgType());
                    po.setRemark(vo.getRemark());
                    tSystemOrganizationMapper.updateByPrimaryKey(po);
                }


            }

        }


        return new ResultBack(true,po);
    }

    public List<TSystemOrganization> getAllOrganization(Integer userId){
        Condition condition = new Condition(TSystemOrganization.class);
        condition.createCriteria().andEqualTo("isDel",false)
               // .andEqualTo("creator",userId)
        ;
        condition.setOrderByClause("id asc");
        List<TSystemOrganization> list = tSystemOrganizationMapper.selectByExample(condition);
        return list;
    }
}
