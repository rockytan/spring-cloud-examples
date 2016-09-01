package com.ulife.cloud.annotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Created by Rocky on 8/23/16.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Group {

    int minCode();

    int maxCode();

    String name();

    String owner();

}