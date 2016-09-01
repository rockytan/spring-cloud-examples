package com.ulife.cloud.portal.utils;

/**
 * Created by Rocky on 8/31/16.
 */
public interface ApiProxy {

    void setProxy(Object proxy);

    Object execute(String... parameters);
}
