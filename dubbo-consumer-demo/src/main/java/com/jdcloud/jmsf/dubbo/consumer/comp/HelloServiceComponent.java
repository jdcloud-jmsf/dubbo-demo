package com.jdcloud.jmsf.dubbo.consumer.comp;

import com.jdcloud.jmsf.demo.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * HelloServiceComponent
 *
 * @author Zhiguo.Chen
 * @since 20211129
 */
@Component
public class HelloServiceComponent {

    @DubboReference(check = false)
    private HelloService helloService;

    public String say() {
        return helloService.hello();
    }

}
