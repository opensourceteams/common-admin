package com.opensourceteam.modules.admin.configuration.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 开发人:刘文
 * 日期:  2018/1/21.
 * 功能描述: 配置接口类的路径
 */
@SpringBootApplication
@MapperScan( basePackages = "com.opensourceteam.modules.dao")
public class MybatisConfiguration {
}
