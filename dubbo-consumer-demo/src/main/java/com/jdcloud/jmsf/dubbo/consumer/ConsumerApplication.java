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
