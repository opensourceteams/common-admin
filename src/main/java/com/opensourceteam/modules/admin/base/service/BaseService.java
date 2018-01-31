package com.opensourceteam.modules.admin.base.service;

import com.opensourceteam.modules.common.core.vo.message.ResultBack;
import com.opensourceteam.modules.po.admin.SystemMenu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Condition;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/1/30.
 * 功能描述:
 */
@Service
public class BaseService{


    /**
     * 得到当前登录用户ID
     * @return
     */
    public Integer getCurrentUserId(){
        return 2;
    }


}
