package com.opensourceteam.modules.common.core.util.string;

/**
 * 开发人:刘文
 * 日期:  2018/1/24.
 * 功能描述:
 */
public class StringUtils {


    /**
     * 得到子字符串最大长度
     * @param str
     * @param maxLength
     * @return
     */
    public static String subString(String str,Integer maxLength){
        if(org.apache.commons.lang3.StringUtils.isEmpty(str)){
            return str;
        }
        if(str.length() > maxLength){
            return  str.substring(0,maxLength);
        }else{
            return str;
        }
    }
}
