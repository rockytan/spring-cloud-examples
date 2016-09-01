package com.ulife.cloud.portal.controller

import com.ulife.cloud.annotation.Api
import com.ulife.cloud.annotation.Parameter
import com.ulife.cloud.portal.domain.ApiInfo
import com.ulife.cloud.portal.domain.Namespace
import com.ulife.cloud.portal.repository.ApiRepository
import com.ulife.cloud.portal.repository.NamespaceRepository
import com.ulife.cloud.portal.utils.ClassLoaderHacker
import com.weibo.api.motan.config.ProtocolConfig
import com.weibo.api.motan.config.RefererConfig
import com.weibo.api.motan.config.RegistryConfig
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.annotation.Resource
import java.util.jar.JarFile

/**
 * Created by Rocky on 8/23/16.
 */
@RestController
@RequestMapping("")
class LoadController {

    @Resource NamespaceRepository namespaceRepository
    @Resource ApiRepository apiRepository

    def static NS = "default"

    @RequestMapping("load")
    def load(){

        def namespace = new Namespace(id:"1",name: NS,path: "/",description: "默认空间")

        if ( !namespaceRepository.exists("id") ){
            namespaceRepository.save(namespace)
        }

        def path = "/Users/Rocky/Git/demo/spring-cloud/demo-service-api/target/demo-service-api-0.0.1-SNAPSHOT.jar"

        def jar = new JarFile(new File(path))
        def export = jar.manifest.mainAttributes.getValue("Api-Export")
        def names = export.split " "

        def loader = new URLClassLoader({new File(path).toURI().toURL()} as URL[],Thread.currentThread().contextClassLoader)

        ClassLoaderHacker.addRepository(new File(path))

        names.each {

            //def clazz = loader.loadClass(it)
            def clazz = Thread.currentThread().contextClassLoader.loadClass(it)
            //def clazz = LoadController.classLoader.loadClass(it)

            def methods = clazz.declaredMethods

            methods.each { o ->
				
                def api = o.getAnnotation(Api.class)
                if( api == null )
                    api = o.getDeclaredAnnotation(Api.class)
                def apiInfo = new ApiInfo(name: api.name(),namespace:namespace,description: api.description(),owner: api.owner(),type: 0,className: it,endpoint: api.name())
                apiRepository.save(apiInfo)


                println(api.name())

                o.parameters.each { p ->
                    println p.getAnnotation(Parameter).name()
                }
            }

            def registryConfig = new RegistryConfig()
            registryConfig.setAddress("localhost:8500")
            registryConfig.setRegProtocol("consul")

            def protocol = new ProtocolConfig()
            protocol.setDefault(true);
            protocol.setName("motan");
            protocol.setMaxContentLength(1048576);

            RefererConfig<?> referer = new RefererConfig<>();
            referer.setInterface(clazz)
            referer.setProtocol(protocol)
            referer.setRegistry(registryConfig)
            def result = clazz.invokeMethod(referer.getRef(),"getUser","1")
            println result

        }

        "success"


    }

    //def static final parameters = {java.net.URL.class} as Class[];

    def addRepository(File jar){

        def loader = (URLClassLoader)ClassLoader.getSystemClassLoader()
        def sysClass = URLClassLoader.class

        def method = sysClass.getDeclaredMethod("addURL",{java.net.URL.class} as Class[])
        method.setAccessible(true)
        method.invoke(loader,{jar.toURI().toURL()} as Object[])

    }
}
