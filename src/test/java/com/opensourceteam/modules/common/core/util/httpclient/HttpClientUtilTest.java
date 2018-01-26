package com.opensourceteam.modules.common.core.util.httpclient;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 开发人:刘文
 * 日期:  2018/1/25.
 * 功能描述:
 */
public class HttpClientUtilTest {
    @Test
    public void postJSONRequest() throws Exception {
        HttpClientUtil.postJSONRequest("https://www.baidu.com");
    }

}