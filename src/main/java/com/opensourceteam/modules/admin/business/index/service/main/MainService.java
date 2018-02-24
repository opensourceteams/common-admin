package com.opensourceteam.modules.admin.business.index.service.main;

import com.opensourceteam.modules.admin.base.service.BaseService;
import com.opensourceteam.modules.admin.business.system.manager.menu.service.SystemMenuService;
import com.opensourceteam.modules.admin.business.system.manager.menu.vo.SystemMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/2/24.
 * 功能描述:
 */
@Service
public class MainService extends BaseService{

    @Autowired
    SystemMenuService systemMenuService;

    public List<SystemMenuVo> getListSystemMenuRelationAll(){
        return systemMenuService.getListSystemMenuRelationAll();
    };

}
