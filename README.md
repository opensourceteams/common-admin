### CRM 后台管理系统

- spring-boot 启动web项目
- spring-boot 集成mybatis,通用mapper
- spring-boot 集成freemarker
- 有两个页面，一个登录，一个主页
- 可点击机构管理，跳到机构管理页面
- 可点击用户管理，跳到用户管理页面

- git:https://github.com/opensourceteams/common-admin
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
