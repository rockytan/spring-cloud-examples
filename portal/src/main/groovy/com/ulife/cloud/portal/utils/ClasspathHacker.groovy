package com.ulife.cloud.portal.utils

/**
 * Created by Rocky on 8/31/16.
 */
class ClasspathHacker {

    def static final parameters = {URL.class} as Class[];

    static addRepository(File jar){

        def loader = (URLClassLoader)ClassLoader.getSystemClassLoader()
        def sysClass = URLClassLoader.class

        def method = sysClass.getDeclaredMethod("addURL",parameters)
        method.setAccessible(true)
        method.invoke(loader,{jar.toURI().toURL()} as Object[])

    }
}
