package com.opensourceteam.modules.common.core.util.json;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import net.logstash.logback.decorate.JsonFactoryDecorator;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/3/2.
 * 功能描述:
 */
public class CustomerJsonFactoryDecorator implements JsonFactoryDecorator {

    @Override
    public MappingJsonFactory decorate(MappingJsonFactory mappingJsonFactory) {
        // 禁用对非ascii码进行escape编码的特性
        mappingJsonFactory.disable(JsonGenerator.Feature.ESCAPE_NON_ASCII);
        return mappingJsonFactory;
    }
}
