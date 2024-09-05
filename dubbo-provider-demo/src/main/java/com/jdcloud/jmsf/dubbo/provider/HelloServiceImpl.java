package com.jdcloud.jmsf.dubbo.provider;

import com.jdcloud.jmsf.demo.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Map;

/**
 * HelloServiceImpl
 *
 * @author Zhiguo.Chen
 * @since 20211130
 */
@DubboService
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello() {
        return "world!";
    }

    @Override
    public Map<String, String> getData1getData1getData1getData1getData1getData1getData1getData1getData1getData1getData1getData1getData1getData1getData1getData1getData1getData1(String code) {
        return null;
    }

    @Override
    public Map<String, String> getData2(String code) {
        return null;
    }

    @Override
    public Map<String, String> getData3(String code) {
        return null;
    }

    @Override
    public Map<String, String> getData4(String code) {
        return null;
    }
}
