package com.atguigu.springcloud.cfgbeans;
 
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
 
@Configuration
public class ConfigBean
{
    @Bean
    @LoadBalanced //開起負載均衡
    public RestTemplate getRestTemplate()
    {
         return new RestTemplate();
    }

}

