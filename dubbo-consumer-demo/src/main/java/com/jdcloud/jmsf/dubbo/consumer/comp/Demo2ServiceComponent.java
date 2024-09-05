package com.jdcloud.jmsf.dubbo.consumer.comp;

import com.jdcloud.jmsf.demo.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component("demo2ServiceComponent")
public class Demo2ServiceComponent implements DemoService {

    @DubboReference(group = "group2", check = false)
    private DemoService demoService;

    @Override
    public String sayHello(String name) {
        return demoService.sayHello(name);
    }

    @Override
    public CompletableFuture<String> sayHelloAsync(String name) {
        return null;
    }

    @Override
    public String getException() {
        int x = 1 / 0;
        return "" + x;
    }

    @Override
    public String sayHelloByTime(String name, Integer time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name + ", sleep=" + time;
    }

    @Override
    public void setException(boolean excep) {

    }
}
