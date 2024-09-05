package com.jdcloud.jmsf.dubbo.consumer;

import com.jdcloud.jmsf.demo.DemoService;
import com.jdcloud.jmsf.dubbo.consumer.comp.DemoServiceComponent;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Slf4j
public class ConsumerApplication {
    /**
     * In order to make sure multicast registry works, need to specify '-Djava.net.preferIPv4Stack=true' before
     * launch the application
     */
    public static void main(String[] args) {
        System.setProperty("JMESH_SERVICE_ID", "CustomConsumerService");
        // System.setProperty("JMESH_REGISTRY_TOKEN", "6fca9ebf-ab1d-8023-9348-e6e12649968f");
        System.setProperty("JMESH_SERVICE_NAMESPACE", "default");
        System.setProperty("JMESH_SERVICE_APP", "jmsf-consumer-demo");
        System.setProperty("JMESH_SERVICE_VERSION", "v1.0");
        System.setProperty("JMESH_SERVICE_GROUP", "group1");
        System.setProperty("JMESH_SERVICE_CLUSTER", "cluster-dev");
        System.setProperty("JMESH_REGISTRY_HOST", "127.0.0.1");
        System.setProperty("JMESH_REGISTRY_PORT", "8500");
        System.setProperty("JMESH_MESH_GROUP", "mesh01");
        System.setProperty("JMESH_SERVICE_DEPLOYMENT", "dep1");
        System.setProperty("ML_AZ", "zone1");
        // System.setProperty("JMESH_ENABLE_MESH", "true");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        DemoService service = null;
        try {
            service = context.getBean("demoServiceComponent", DemoServiceComponent.class);
        } catch (Exception e) {
            log.error("", e);
        }
        while (true) {
            try {
                if (null == service) {
                    service = context.getBean("demoServiceComponent", DemoServiceComponent.class);
                } else {
                    String hello = service.sayHello("world");
                    log.info("[JMSF]result :" + hello);
                }
                Thread.sleep(10000);
            } catch (Exception e) {
                log.error("[JMSF]invoke error!", e);
            }
        }
    }

    @Configuration(proxyBeanMethods = false)
    @EnableDubbo(scanBasePackages = "com.jdcloud.jmsf.dubbo.consumer.comp")
    @PropertySource({"classpath:/spring/dubbo-consumer.properties", "classpath:/dubbo.properties"})
    @ComponentScan(value = {"com.jdcloud.jmsf.dubbo.consumer.comp"})
    static class ConsumerConfiguration {

    }
}
