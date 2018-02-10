package com.opensourceteam.modules.dao.admin;

import com.opensourceteam.modules.admin.configuration.spring.CustomMVCConfiguration;
import com.opensourceteam.modules.po.admin.SystemUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/2/10.
 * 功能描述:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes ={CustomMVCConfiguration.class} )
public class SystemUserMapperTest {

    @Autowired
    SystemUserMapper systemUserMapper;

    @Test
    public void update(){
        SystemUser systemUser = new SystemUser();
        systemUser.setId(1);
        systemUser.setLoginPwd("aea11564be1f672dcade343f7f886980");
        systemUserMapper.updateByPrimaryKeySelective(systemUser);
    }
}