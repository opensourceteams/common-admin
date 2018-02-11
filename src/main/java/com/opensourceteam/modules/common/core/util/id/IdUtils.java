package com.opensourceteam.modules.common.core.util.id;

import com.opensourceteam.modules.enume.BusinessTypeEnume;
import org.apache.commons.lang3.StringUtils;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/2/8.
 * 功能描述: id 处理
 */
public class IdUtils {

    /**
     * 将 int ID 转成带前缀的字符串id
     * @param businessTypeEnume
     * @param id
     * @return
     */
    public static String getPrefixId(BusinessTypeEnume businessTypeEnume, Integer id){
        if(id == null ){
            return businessTypeEnume.getPrefix() + "_";
        }else{
            return businessTypeEnume.getPrefix() + "_" + Integer.toString(id);
        }
    }

    /**
     * 得到去掉前缀的Integer id
     * @param id
     * @return
     */
    public static Integer getRemovePrefixId(String id){
        if(StringUtils.isBlank(id)){
            return null;
        }else{
            if(id.contains("_")){
                return Integer.parseInt(id.split("_")[1]);
            }

        }
        return  null;
    }

    /**
     * 得到去掉前缀的Integer id
     * @param id
     * @return
     */
    public static Integer getRemovePrefixId(String prefix,String id){
        if(StringUtils.isBlank(id)){
            return null;
        }else{
            if(id.contains("_") && id.contains(prefix)){
                return Integer.parseInt(id.split("_")[1]);
            }

        }
        return  null;
    }
}
