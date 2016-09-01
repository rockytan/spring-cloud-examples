package com.ulife.cloud.portal.controller;

import com.ulife.cloud.portal.utils.ApiProxy;
import com.ulife.cloud.portal.utils.ClassLoaderHacker;
import com.weibo.api.motan.config.ProtocolConfig;
import com.weibo.api.motan.config.RefererConfig;
import com.weibo.api.motan.config.RegistryConfig;
import com.weibo.api.motan.registry.consul.client.MotanConsulClient;
import javassist.*;
import javassist.Modifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.concurrent.ThreadFactory;
import java.util.jar.JarFile;
import java.util.stream.Stream;

/**
 * Created by Rocky on 8/31/16.
 */
@RestController
public class MainController {

    @RequestMapping("init")
    public String init() throws Exception {

        String path = "/Users/Rocky/Git/demo/spring-cloud/demo-service-api/target/demo-service-api-0.0.1-SNAPSHOT.jar";
        File file = new File(path);

        JarFile jar = new JarFile(file);
        String export = jar.getManifest().getMainAttributes().getValue("Api-Export");
        String[] names = export.split(" ");

        ClassLoaderHacker.addRepository(file);

        Stream.of(names).forEach( name -> {

            try {
                Class clazz = Thread.currentThread().getContextClassLoader().loadClass(name);

                RegistryConfig registry = new RegistryConfig();
                registry.setAddress("localhost:8500");
                registry.setRegProtocol("consul");

                ProtocolConfig protocol = new ProtocolConfig();
                protocol.setDefault(true);
                protocol.setName("motan");
                protocol.setMaxContentLength(1048576);

                RefererConfig<?> referer = new RefererConfig<>();
                referer.setInterface(clazz);
                referer.setProtocol(protocol);
                referer.setRegistry(registry);

                ClassPool pool = ClassPool.getDefault();
                CtClass cls = pool.makeClass("UserService.getUser");
                cls.addInterface(pool.getCtClass("com.ulife.cloud.portal.utils.ApiProxy"));

                CtField proxy = new CtField(pool.get("java.lang.Object"),"proxy",cls);
                proxy.setModifiers(Modifier.PRIVATE);

                cls.addField(proxy);
                cls.addMethod(CtNewMethod.setter("setProxy",proxy));

                CtMethod execute = CtNewMethod.make("public Object execute(String[] parameters){" +
                        "return ((com.ulife.cloud.demo.UserService)proxy).getUser(parameters[0]);" +
                        "}",cls);
                cls.addMethod(execute);

                System.out.println(cls.toString());
                File out = new File("getUser.class");
                FileOutputStream fos = new FileOutputStream(out);
                fos.write(cls.toBytecode());
                fos.close();
                System.out.println(out.getCanonicalPath());

                Method getUser = clazz.getMethod("getUser",String.class);
                Object result = getUser.invoke(referer.getRef(),"1");
                System.out.println(result);

                ApiProxy apiProxy = (ApiProxy)cls.toClass().newInstance();
                apiProxy.setProxy(referer.getRef());
                System.out.println(apiProxy.execute("1"));

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (CannotCompileException e) {
                e.printStackTrace();
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        });



        return "success";

    }

}
