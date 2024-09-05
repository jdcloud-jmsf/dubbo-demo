/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jdcloud.jmsf.dubbo.provider;

import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class ProviderApplication {

    public static void main(String[] args) throws Exception {
        System.setProperty("JMESH_SERVICE_ID", "CustomProviderService");
        // System.setProperty("JMESH_REGISTRY_TOKEN", "6fca9ebf-ab1d-8023-9348-e6e12649968f");
        System.setProperty("JMESH_SERVICE_NAMESPACE", "default");
        System.setProperty("JMESH_SERVICE_APP", "jmsf-provider-demo");
        System.setProperty("JMESH_SERVICE_VERSION", "v1.0");
        System.setProperty("JMESH_SERVICE_GROUP", "group1");
        System.setProperty("JMESH_SERVICE_CLUSTER", "cluster-dev");
        System.setProperty("JMESH_REGISTRY_HOST", "127.0.0.1");
        System.setProperty("JMESH_REGISTRY_PORT", "8500");
        System.setProperty("JMESH_MESH_GROUP", "mesh01");
        System.setProperty("JMESH_SERVICE_DEPLOYMENT", "dep1");
        System.setProperty("ML_AZ", "zone1");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        context.start();
        System.in.read();
        Thread.currentThread().join();
    }

    @Configuration(proxyBeanMethods = false)
    @EnableDubbo(scanBasePackages = "com.jdcloud.jmsf.dubbo.provider")
    @PropertySource("classpath:/spring/dubbo-provider.properties")
    static class ProviderConfiguration {

        @Bean
        public RegistryConfig registryConfig() {
            RegistryConfig registryConfig = new RegistryConfig();
            registryConfig.setAddress("jmsfconsul://127.0.0.1:8500");
            // registryConfig.setGroup("dev");
            return registryConfig;
        }
    }
}
