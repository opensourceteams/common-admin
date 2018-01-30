package com.opensourceteam.modules.dao.admin;

import com.opensourceteam.modules.admin.configuration.spring.CustomMVCConfiguration;
import com.opensourceteam.modules.po.admin.TBusinessShow;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 开发人:刘文
 * 日期:  2018/1/25.
 * 功能描述:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes ={CustomMVCConfiguration.class} )
public class TBusinessShowMapperTest {

    Logger logger = LoggerFactory.getLogger(TBusinessShowMapperTest.class) ;

    @Autowired
    TBusinessShowMapper mapper;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * 插入一条数据
     */
    @Test
    public void insert(){
        TBusinessShow test = new TBusinessShow();
        String value = "89" ;
        test.setBusinessId("01" + value);
        test.setBusinessName("name"  + value);
        test.setRemark("中文remark_" + value);
        test.setCreateDate(new Date());
        test.setIsDel(false);
        mapper.insert(test);
    }


    /**
     * 插入一条数据
     */
    @Test
    public void insertBatch(){
        for( int i = 0 ; i < 10 ; i++){
            TBusinessShow test = new TBusinessShow();
            String value = "" + i ;
            test.setBusinessId("01" + value);
            test.setBusinessName("name"  + value);
            test.setRemark("中文remark_" + value);
            test.setCreateDate(new Date());
            test.setIsDel(false);
            mapper.insert(test);
        }

    }

    /**
     * 更新一条数据(根据主键更新)
     */
    @Test
    public void update(){
        TBusinessShow test = new TBusinessShow();
        String value = "3" ;
        test.setId(1);
        test.setBusinessName("test_" + value);
        test.setBusinessId("11");
        test.setCreateDate(new Date());
        test.setIsDel(false);
        test.setRemark("中文remark_" + value);
        mapper.updateByPrimaryKey(test);
    }

    /**
     * 删除一条数据(根据主键)
     */
    @Test
    public void deleteByPrimaryKey(){
        mapper.deleteByPrimaryKey(2);
    }

    /**
     * 删除一条数据(根据对象的主键值值)
     */
    @Test
    public void delete(){
        TBusinessShow test = new TBusinessShow();
        test.setId(2);
        mapper.delete(test);
    }

    @Test
    public void selectAll(){
        List<TBusinessShow> list = mapper.selectAll();
        for(TBusinessShow po : list){
            logger.debug("po:{}",po);
        }
    }

    /**
     * 查询后排序
     */
    @Test
    public void orderBy(){

        Condition condition = new Condition(TBusinessShow.class);
        condition.setOrderByClause("age desc,name desc");
        List<TBusinessShow> list = mapper.selectByExample(condition);
        for(TBusinessShow po : list){
            logger.debug("po:{}",po);
        }
        System.out.println();
    }


}