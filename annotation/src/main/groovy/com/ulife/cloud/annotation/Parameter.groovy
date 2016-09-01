package com.ulife.cloud.annotation

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Created by Rocky on 8/24/16.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@interface Parameter {

    String name()

    String description()

    boolean required() default true

    String defaultValue() default ""

}
