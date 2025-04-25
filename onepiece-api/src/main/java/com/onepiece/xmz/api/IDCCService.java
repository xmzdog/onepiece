package com.onepiece.xmz.api;


import com.onepiece.xmz.api.response.Response;

/**
 * @description DCC 动态配置中心
 */
public interface IDCCService {

    Response<Boolean> updateConfig(String key, String value);

}
