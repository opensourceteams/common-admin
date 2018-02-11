package com.opensourceteam.modules.common.core.util.encrypt.md5;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 开发人:刘文
 * QQ: 372065525
 * 日期:  2018/2/11.
 * 功能描述:
 */
public class MD5Util {

    public static Integer hashIterations = 1024 ;
    public static String algorithmName = "MD5" ;

    /**
     * 用md5加密
     * @param username
     * @param password
     * @return
     */
    public static String encryptMd5(String username,String password){
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
        String newPasspword = new SimpleHash(algorithmName, password, salt, hashIterations).toHex();
        return newPasspword;
    }
}
