package com.opensourceteam.modules.admin.business.system.manager.user.service;

import com.opensourceteam.modules.admin.configuration.spring.CustomMVCConfiguration;
import com.opensourceteam.modules.common.core.util.encrypt.md5.MD5Util;
import com.opensourceteam.modules.po.admin.SystemUser;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class SystemUserServiceTest {
    Logger logger = LoggerFactory.getLogger(SystemUserServiceTest.class);

    @Autowired
    SystemUserService systemUserService;
    @Test
    public void getUserByLoginId() throws Exception {
        SystemUser systemUser = systemUserService.getUserByLoginId("root");
        logger.debug("systemUser:{}",systemUser);
    }

    /**
     * 加密后的密码
     * @throws Exception
     */
    @Test
    public void pwd_Md5() throws Exception {
        String username = "root";
        String password = "0";
        String newPasspword = MD5Util.encryptMd5(username,password);
        logger.debug("newPasspword:{} ,length:{}",newPasspword ,newPasspword.length() );
    }

    /**
     * 加密后的密码
     * @throws Exception
     */
    @Test
    public void pwd_Sha512Hash() throws Exception {
        String username = "root";
        String password = "0";
        // 将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(username);
          /*
        * MD5加密：
        * 使用SimpleHash类对原始密码进行加密。
        * 第一个参数代表使用MD5方式加密
        * 第二个参数为原始密码
        * 第三个参数为盐值，即用户名
        * 第四个参数为加密次数
        * 最后用toHex()方法将加密后的密码转成String
        * */
        String newPasspword = new Sha512Hash().toHex();
        logger.debug("newPasspword:{} ,length:{}",newPasspword ,newPasspword.length() );
    }

}