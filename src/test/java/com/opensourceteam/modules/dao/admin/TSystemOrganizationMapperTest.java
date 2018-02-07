package com.opensourceteam.modules.dao.admin;

import com.opensourceteam.modules.admin.configuration.spring.CustomMVCConfiguration;
import com.opensourceteam.modules.enume.IconTypeEnume;
import com.opensourceteam.modules.po.admin.TSystemOrganization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;



/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/1/28.
 * 功能描述:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes ={CustomMVCConfiguration.class} )
public class TSystemOrganizationMapperTest {
    Logger logger = LoggerFactory.getLogger(TSystemOrganizationMapperTest.class) ;

    @Autowired
    TSystemOrganizationMapper mapper;

    @Test
    public void insert(){

        TSystemOrganization po = new TSystemOrganization();

        po.setName("总公司");
        po.setOrgType(IconTypeEnume.Organization.getValue()+"");
        po.setParentId(0);
        po.setCreateDate(new Date());
        po.setCreator(0);
        po.setIsDel(false);

        mapper.insert(po) ;

        po.setParentIds("/0/" +po.getId());
        mapper.updateByPrimaryKey(po);
    }

    @Test
    public void selectAll(){
        List<TSystemOrganization> list = mapper.selectAll();
        for(TSystemOrganization po : list){
            logger.debug("po:{}",po);
        }
    }
}