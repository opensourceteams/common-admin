### CRM 后台管理系统

- spring-boot 启动web项目
- spring-boot 集成mybatis,通用mapper
- spring-boot 集成freemarker
- 有两个页面，一个登录，一个主页
- 可点击机构管理，跳到机构管理页面
- 可点击用户管理，跳到用户管理页面

- git:https://github.com/opensourceteams/common-admin
## common_admin_v1.0.7
###  登录用户名、密码验码
- 登录用户名、密码验码
- 密码 md5 加密
- 全局异常处理(用户名不存在、密码不对)
- bootstrap 4 页面显示验证结果
- 登录地址:http://localhost:9096/doLogin
- 用户名，密码:  root / 0

## shiro 配置

```java

package com.opensourceteam.modules.admin.configuration.shiro.configuration;

import com.opensourceteam.modules.admin.configuration.shiro.realm.CustomerShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //以下是过滤链，按顺序过滤，所以/**需要放最后
        //开放的静态资源
        filterChainDefinitionMap.put("/static/**", "anon");//网站图标
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/doLogin", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(myRealm(matcher));
        return defaultWebSecurityManager;
    }

    @Bean
    public CustomerShiroRealm myRealm(HashedCredentialsMatcher matcher) {
        CustomerShiroRealm customerShiroRealm = new CustomerShiroRealm();
        customerShiroRealm.setCredentialsMatcher(matcher);
        return customerShiroRealm;
    }

    /**
     * 密码匹配凭证管理器
     *
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 采用MD5方式加密
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 设置加密次数
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;

    }
}
```
## 密码加密处理(单元测试)

```java
/**
     * 加密后的密码
     * @throws Exception
     */
    @Test
    public void pwd_Md5() throws Exception {
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
        String newPasspword = new SimpleHash("MD5", password, salt, 1024).toHex();
        logger.debug("newPasspword:{} ,length:{}",newPasspword ,newPasspword.length() );
    }
```
## 登录验证 LoginController.java

```java
    @RequestMapping("/doLogin")
    public ModelAndView doLogin(String loginId,String password) {
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        UsernamePasswordToken token = new UsernamePasswordToken(loginId,password,IpUtil.getIpAddr(request));
        SecurityUtils.getSubject().login(token);
        if( SecurityUtils.getSubject().isAuthenticated()){
            modelAndView = new ModelAndView("redirect:/main");
        }

        return modelAndView;
    }
```
## 增加登录验证异常处理

```java
package com.opensourceteam.modules.admin.configuration.spring.exception.handler;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 开发人:刘文 2
 * QQ: 372065525
 * 日期:  2018/2/10.
 * 功能描述:
 */
@ControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(CustomerExceptionHandler.class);

    /**
     * 没有权限访问
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
   // @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handleException(AuthorizationException e) {
        ModelAndView modelAndView = new ModelAndView("/modules/common/transfer/transfer");
        modelAndView.addObject("url","/module/common/message/show");
        modelAndView.addObject("message","没有权限访问");
        logger.debug("[CustomerExceptionHandler] 无权限访问处理");
        return modelAndView;
    }

    /**
     * 用户不存在　
     * @param e
     * @return
     */
    @ExceptionHandler(UnknownAccountException.class)
    public ModelAndView handleExceptionUnknownAccountException(UnknownAccountException e) {
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("message","用户不存在");
        modelAndView.addObject("code","1");
        logger.debug("[CustomerExceptionHandler] 用户不存在");
        return modelAndView;
    }

    /**
     * 密码错误　
     * @param e
     * @return
     */
    @ExceptionHandler(IncorrectCredentialsException.class)
    public ModelAndView handleExceptionIncorrectCredentialsException(IncorrectCredentialsException e) {
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("code","2");
        modelAndView.addObject("message","密码错误");
        logger.debug("[CustomerExceptionHandler] 密码错误");
        return modelAndView;
    }




}

```
## 增加登录页面验证提示

```html
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="static/project/favicon.ico">

    <title>登录</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/static/modules/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="static/modules/bootstrap/bootstrap-4.0.0/examples/signin.css" rel="stylesheet">
</head>

<body class="text-center">

<form class="form-signin" action="doLogin" method="post">

    <#--<img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">-->
    <h1 class="h3 mb-3 font-weight-normal">登录</h1>

    <div class="form-group <#if message?? && code == "1" > has-warning </#if> ">
        <input type="text" name="loginId" class="form-control form-control-warning" placeholder="用户名" required autofocus>
    </div>

    <div class="form-group <#if message?? && code == "2" > has-warning </#if> ">
        <input type="text" name="password" class="form-control form-control-warning" placeholder="密码" required>
    </div>

    <div class="form-group <#if message??> has-warning </#if> ">
        <div class="form-control-feedback">${message!''}</div>
    </div>


    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> 记住密码
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
</form>



</body>
</html>

```



## common_admin_v1.0.6
###  菜单功能，增加权限控制
- 菜单功能增加权限控制
- 菜单权限 
```java
admin:systemmanager:menu:listView
```
- 用户权限验证
```java
 /**
     * 权限认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("[CustomerShiroRealm doGetAuthorizationInfo]");
        String loginId = principalCollection.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Collection<String> permissions = new ArrayList<>();
        permissions.add("admin:systemmanager:menu:listView1");
        info.addStringPermissions(permissions);
        return info;
    }
```

- 无权限访问异常处理

```java
package com.opensourceteam.modules.admin.configuration.spring.exception.handler;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 开发人:刘文 2
 * QQ: 372065525
 * 日期:  2018/2/10.
 * 功能描述:
 */
@ControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(CustomerExceptionHandler.class);

    @ExceptionHandler(AuthorizationException.class)
   // @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handleException(AuthorizationException e) {
        ModelAndView modelAndView = new ModelAndView("/modules/common/transfer/transfer");
        modelAndView.addObject("url","/module/common/message/show");
        modelAndView.addObject("message","没有权限访问");
        logger.debug("[CustomerExceptionHandler] 无权限访问处理");
        return modelAndView;
    }
}

```
- 中转消息提示页面(跳顶级页面) transfer.ftl
```javascript
<#--转换主页面-->
<script type="text/javascript">
    $(document).ready(function(){
        window.location.href= "${url}?message=${message}";
    });
</script>


```
- 消息提示 controller MessageController.java

```java
package com.opensourceteam.modules.admin.business.common.message.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/2/10.
 * 功能描述:
 */
@Controller
@RequestMapping("/module/common/message")
public class MessageController {

    @RequestMapping("/show")
    public ModelAndView show(String message) {
        ModelAndView modelAndView = new ModelAndView("/modules/common/message/message");
        modelAndView.addObject("message",message) ;
        return modelAndView;
    }
}

```
- 消息展示页面 message.ftl

```html
<h1> ${message}</h1>
```


## common_admin_v1.0.5
###  权限管理、角色管理
- 权限增、删、查、修改
- 角色配权限，增、删、查、修改

## common_admin_v1.0.4
###  菜单管理
- ztree增、删、查、修改
- 批量删、增加根节点

## common_admin_v1.0.3
### 人员树
- ztree 维护树
- 人员树（增、删、改、查）
- 人员树，人员可修改，可删，机构只可增加人员，不可删除

## common_admin_v1.0.2
### 机构树
- 机构树（增、删、改、查）
