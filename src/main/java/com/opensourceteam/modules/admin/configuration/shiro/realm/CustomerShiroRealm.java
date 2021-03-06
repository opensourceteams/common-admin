package com.opensourceteam.modules.admin.configuration.shiro.realm;

import com.opensourceteam.modules.admin.business.system.manager.user.service.SystemUserService;
import com.opensourceteam.modules.po.admin.SystemUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/2/9.
 * 功能描述:
 */
public class CustomerShiroRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(CustomerShiroRealm.class);

    @Autowired
    SystemUserService systemUserService;

    /**
     * 权限认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Collection<String> permissions = systemUserService.getCurrentUserPermissionStringList();
        info.addStringPermissions(permissions);
        return info;
    }



    /**
     * 用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("[CustomerShiroRealm doGetAuthenticationInfo] 身份认证");


        if( authenticationToken instanceof UsernamePasswordToken){
            UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
            SystemUser systemUser =systemUserService.getUserByLoginId(usernamePasswordToken.getUsername());
            if(systemUser !=null){
                // 获取盐值，即用户名
                ByteSource salt = ByteSource.Util.bytes(systemUser.getLoginId());
                String password = new String( usernamePasswordToken.getPassword());
                SimpleAuthenticationInfo info = new SimpleAuthenticationInfo( systemUser.getLoginId(),  systemUser.getLoginPwd(),salt,getName());
                return info;
            }

        }
        return null;
    }
}
