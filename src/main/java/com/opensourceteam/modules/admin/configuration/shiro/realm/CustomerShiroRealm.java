package com.opensourceteam.modules.admin.configuration.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/2/9.
 * 功能描述:
 */
//@Component("authorizer")
public class CustomerShiroRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(CustomerShiroRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("[CustomerShiroRealm doGetAuthorizationInfo]");
        String loginId = principalCollection.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Collection<String> permissions = new ArrayList<>();
        permissions.add("admin:systemmanager:menu:listView");
        info.addStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("[CustomerShiroRealm doGetAuthenticationInfo] 身份认证");

        if( authenticationToken instanceof UsernamePasswordToken){
            UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;

            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo( usernamePasswordToken.getUsername(),  usernamePasswordToken.getPassword(),getName());
            return info;
        }
        //获得当前用户的用户名
        String username = (String) authenticationToken.getPrincipal();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, "000000",getName());
        return info;
    }
}
