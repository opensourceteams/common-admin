### spring-boot 集成 mybatis 通用mapper

- spring-boot 启动web项目
- spring-boot 集成mybatis,通用mapper
- 查看两个网站是否可以正常访问
- 将响应结果入库记录
- git:https://gitee.com/opensourceteams/n_10001_vcn_monitor_online

## pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.springframework</groupId>
    <artifactId>n_100006_spring-boot-devtools-tomcat-jsp</artifactId>
    <version>0.1.0</version>
    <packaging>war</packaging>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.9.RELEASE</version>
    </parent>
    <properties>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <mapper.version>3.5.0</mapper.version>
    </properties>


    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>




        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>4.3.2.RELEASE</version>
            <scope>test</scope>
        </dependency>

        <!-- Tomcat embedded container-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <!--  <scope>provided</scope>-->
        </dependency>
        <!-- JSTL for JSP -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
        </dependency>

        <!-- Need this to compile JSP -->
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
            <!-- <scope>provided</scope>-->
        </dependency>


        <!-- 热部署 第一步 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
            <scope>true</scope>
        </dependency>



        <!-- mybatis begin -->

        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.1</version>
        </dependency>

        <!-- mybatis mapper插件，通用mapper-->
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper-spring-boot-starter</artifactId>
            <version>1.1.7</version>
        </dependency>


        <!-- mybatis end -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql.version}</version>
        </dependency>



        <!--pagehelper-->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>RELEASE</version>
        </dependency>


    </dependencies>



    <build>
        <sourceDirectory>src/main/java </sourceDirectory>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
            </resource>
        </resources>

        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <!--允许linux上注册服务-->
                <configuration>
                    <jvmArguments>
                        -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005
                    </jvmArguments>
                    <executable>true</executable>
                </configuration>
                <dependencies>
                    <dependency>
                        <groupId>org.springframework</groupId>
                        <artifactId>springloaded</artifactId>
                        <version>1.2.8.RELEASE</version>
                    </dependency>
                </dependencies>
            </plugin>


            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.6</version>
                <configuration>
                    <configurationFile>src/main/resources/config/mybatis/generator/generatorConfig.xml</configurationFile>
                    <overwrite>true</overwrite>
                    <verbose>true</verbose>
                </configuration>
                <dependencies>
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>${mysql.version}</version>
                    </dependency>
                    <dependency>
                        <groupId>tk.mybatis</groupId>
                        <artifactId>mapper</artifactId>
                        <version>${mapper.version}</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>

</project>
```
## application.properties
```xml
#web server 端口号
server.port=9095
#指定用哪套配置文件
spring.profiles.active=dev
spring.http.encoding.force=true
spring.http.encoding.charset=UTF-8
spring.http.encoding.enabled=true
server.tomcat.uri-encoding=UTF-8

#jsp 路径前缀
spring.mvc.view.prefix=/WEB-INF/jsp/
#jsp 文件后缀
spring.mvc.view.suffix= .jsp
application.message= Hello Phil

#mybatis
#mybatis实体类包
mybatis.type-aliases-package=com.opensourceteam.modules.admin.business.test.po
#mybatis xml文件映射
mybatis.mapper-locations=classpath:com/opensourceteam/modules/business/test/mapper/*.xml


#mappers 多个接口时逗号隔开
mapper.mappers= tk.mybatis.mapper.common.Mapper
mapper.not-empty=false
mapper.identity=MYSQL
#uuid需要，先生成主键，再插入
mapper.ORDER=BEFORE

#pagehelper
pagehelper.helperDialect=mysql
pagehelper.reasonable=true
pagehelper.supportMethodsArguments=true
pagehelper.params=count=countSql





```

## application-dev.properties

```xml

#datasource

debug=true
logging.level.root=debug
logging.level.tk.mybatis.springboot.mapper=trace
spring.datasource.url=jdbc:mysql://localhost:3306/db_mybatis
spring.datasource.username=root
spring.datasource.password=000000
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.druid.initial-size=1
spring.datasource.druid.min-idle=1
spring.datasource.druid.max-active=20
spring.datasource.druid.test-on-borrow=true
spring.datasource.druid.stat-view-servlet.allow=true

spring.datasource.testWhileIdle=true
spring.datasource.validationQuery=SELECT 1
```

##自动生成配置
###  generatorConfig.xml 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
    <properties resource="config/mybatis/generator/config.properties"></properties>

    <context id="Mysql" targetRuntime="MyBatis3Simple" defaultModelType="flat">
        <property name="beginningDelimiter" value="`"/>
        <property name="endingDelimiter" value="`"/>

        <plugin type="${mapper.plugin}">
            <property name="mappers" value="${mapper.Mapper}"/>
        </plugin>

        <plugin type="org.mybatis.generator.plugins.ToStringPlugin" />
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="${jdbc.url}"
                        userId="${jdbc.user}"
                        password="${jdbc.password}">
        </jdbcConnection>

        <!--生成 po -->
        <javaModelGenerator targetPackage="com.opensourceteam.modules.admin.business.test.po" targetProject="src\main\java"/>
        <!--生成mapper.xml文件 -->
        <sqlMapGenerator targetPackage="com.opensourceteam.modules.admin.business.test.mapper"  targetProject="src\main\resources"/>
        <!--生成dao -->
        <javaClientGenerator targetPackage="com.opensourceteam.modules.admin.business.test.dao" targetProject="src\main\java" type="XMLMAPPER" />

        <table tableName="t_test" >
            <!-- mysql 主键自增长-->
            <generatedKey column="id" sqlStatement="Mysql" identity="true"/>
        </table>
        <table tableName="t_test_uuid" >
            <!-- uuid-->
            <generatedKey column="id" sqlStatement="select replace(uuid(), ''-'', '''')" identity="false" type="pre"/>
        </table>



    </context>
</generatorConfiguration>
```
### config.properties
```java
# 数据库配置
jdbc.driverClass = com.mysql.jdbc.Driver
jdbc.url = jdbc:mysql://localhost:3306/db_mybatis
jdbc.user = root
jdbc.password = 000000



# 通用Mapper配置
mapper.plugin = tk.mybatis.mapper.generator.MapperPlugin
#mapper.Mapper = tk.mybatis.mapper.common.Mapper
#mapper.Mapper = tk.mybatis.springboot.util.MyMapper
mapper.Mapper = tk.mybatis.mapper.common.Mapper
```

### 自动生成命令
```shell
set MAVEN_OPTS="-Dfile.encoding=UTF-8"
mvn  -Dmybatis.generator.overwrite=true  mybatis-generator:generate -X
```

## 单元测试
### 自增长ID，CRUD
```java
package com.opensourceteam.modules.admin.business.test.dao.crud;

import com.opensourceteam.modules.admin.business.test.dao.TTestMapper;
import com.opensourceteam.modules.admin.business.test.po.TTest;
import com.opensourceteam.modules.admin.configuration.spring.CustomMVCConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 开发人:刘文
 * 日期:  2018/1/22.
 * 功能描述:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes ={CustomMVCConfiguration.class} )
public class TestCRUDMybatis {
    Logger logger = LoggerFactory.getLogger(TestCRUDMybatis.class) ;

    @Autowired
    TTestMapper tTestMapper;
    /**
     * 插入一条数据
     */
    @Test
    public void insert(){
        TTest test = new TTest();
        String value = "11" ;
        test.setName("test_" + value);
        test.setRemark("中文remark_" + value);
        tTestMapper.insert(test);
    }

    /**
     * 更新一条数据(根据主键更新)
     */
    @Test
    public void update(){
        TTest test = new TTest();
        String value = "22" ;
        test.setId(2);
        test.setName("test_" + value);
        test.setRemark("中文remark_" + value);
        tTestMapper.updateByPrimaryKey(test);
    }

    /**
     * 删除一条数据(根据主键)
     */
    @Test
    public void deleteByPrimaryKey(){
        tTestMapper.deleteByPrimaryKey(2);
    }

    /**
     * 删除一条数据(根据对象的主键值值)
     */
    @Test
    public void delete(){
        TTest test = new TTest();
        test.setId(5);
        tTestMapper.delete(test);
    }

    @Test
    public void selectAll(){
        List<TTest> list = tTestMapper.selectAll();
        for(TTest po : list){
            logger.debug("po:{}",po);
        }
    }

}

```

### UUID主键，单元测试
```java
package com.opensourceteam.modules.admin.business.test.dao.crud;

import com.opensourceteam.modules.admin.business.test.dao.TTestMapper;
import com.opensourceteam.modules.admin.business.test.dao.TTestUuidMapper;
import com.opensourceteam.modules.admin.business.test.po.TTest;
import com.opensourceteam.modules.admin.business.test.po.TTestUuid;
import com.opensourceteam.modules.admin.configuration.spring.CustomMVCConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * 开发人:刘文
 * 日期:  2018/1/22.
 * 功能描述:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes ={CustomMVCConfiguration.class} )
public class TestCRUDMybatisUUID {
    Logger logger = LoggerFactory.getLogger(TestCRUDMybatisUUID.class) ;

    @Autowired
    TTestUuidMapper mapper;


    /**
     * 插入一条数据
     */
    @Test
    public void insert(){
        TTestUuid test = new TTestUuid();
        String value = "89" ;
        test.setName("test_" + value);
        test.setAge(20);
        test.setRemark("中文remark_" + value);
        mapper.insert(test);
    }


    /**
     * 插入一条数据
     */
    @Test
    public void insertBatch(){
        for( int i = 0 ; i < 10 ; i++){
            TTestUuid test = new TTestUuid();
            String value = "" + i ;
            test.setName("test_" + value);
            test.setRemark("中文remark_" + value);
            test.setAge(20);
            mapper.insert(test);
        }

    }

    /**
     * 更新一条数据(根据主键更新)
     */
    @Test
    public void update(){
        TTestUuid test = new TTestUuid();
        String value = "3" ;
        test.setId("75a406d1fbec11e7af7b00fff3451ede");
        test.setName("test_" + value);
        test.setRemark("中文remark_" + value);
        mapper.updateByPrimaryKey(test);
    }

    /**
     * 删除一条数据(根据主键)
     */
    @Test
    public void deleteByPrimaryKey(){
        mapper.deleteByPrimaryKey("75a406d1fbec11e7af7b00fff3451ede");
    }

    /**
     * 删除一条数据(根据对象的主键值值)
     */
    @Test
    public void delete(){
        TTestUuid test = new TTestUuid();
        test.setId("42b1c49dfbec11e7af7b00fff3451ede");
        mapper.delete(test);
    }

    @Test
    public void selectAll(){
        List<TTestUuid> list = mapper.selectAll();
        for(TTestUuid po : list){
            logger.debug("po:{}",po);
        }
    }

    /**
     * 查询后排序
     */
    @Test
    public void orderBy(){

        Condition condition = new Condition(TTestUuid.class);
        condition.setOrderByClause("age desc,name desc");
        List<TTestUuid> list = mapper.selectByExample(condition);
        for(TTestUuid po : list){
            logger.debug("po:{}",po);
        }
        System.out.println();
    }

}

```

### PageHelper单元测试

```java
package com.opensourceteam.modules.admin.business.test.dao.crud;

import com.github.pagehelper.PageHelper;
import com.opensourceteam.modules.admin.business.test.dao.TTestUuidMapper;
import com.opensourceteam.modules.admin.business.test.po.TTestUuid;
import com.opensourceteam.modules.admin.configuration.spring.CustomMVCConfiguration;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * 开发人:刘文
 * 日期:  2018/1/22.
 * 功能描述:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes ={CustomMVCConfiguration.class} )
public class TestPageHelper {
    Logger logger = LoggerFactory.getLogger(TestPageHelper.class) ;

    @Autowired
    TTestUuidMapper mapper;

    /**
     * 排序后分页
     */
    @Test
    public void pageAndOrder(){
        /**
         * pageNum: 页数
         * pageSize: 每页的数量
         */
        PageHelper.startPage(3, 10);
        Condition condition = new Condition(TTestUuid.class);
        condition.setOrderByClause("age desc,name desc");
        List<TTestUuid> list = mapper.selectByExample(condition);
        for(TTestUuid po : list){
            logger.debug("po:{}",po);
        }
        System.out.println();
    }
    /**
     * 分页查询
     */
    @Test
    public void selectPageHelperStartPage(){
        /**
         * pageNum: 页数
         * pageSize: 每页的数量
         */
        PageHelper.startPage(2, 10);
        List<TTestUuid> list = mapper.select(null);
        for(TTestUuid po : list){
            logger.debug("po:{}",po);
        }
        System.out.println();
    }

    /**
     * 分页查询
     */
    @Test
    public void selectPageHelperStartPage2(){
        for(int i = 0 ; i< 10 ;i ++){
            /**
             * pageNum: 页数 (页数从1开始)
             * pageSize: 每页的数量
             */
            PageHelper.startPage(i, 10);
            logger.debug("页数:{}",i);
            List<TTestUuid> list = mapper.select(null);

            for(TTestUuid po : list){
                logger.debug("po:{}",po);
            }
            System.out.println();
        }
    }


    /**
     * 分页查询
     */
    @Test
    public void selectRowBoundsMapper(){
        TTestUuid query = new TTestUuid();
        for( int i = 1; i <= 10 ; i++){
            logger.debug("页数:{}",i);
            /**
             * offset: 页数
             * limit: 每页的数量
             */
            RowBounds rowBounds = new RowBounds(i,10);
            List<TTestUuid> list = mapper.selectByRowBounds(query,rowBounds) ;
            for(TTestUuid po : list){
                logger.debug("po:{}",po);
            }
            System.out.println();
        }

    }

}

```







































