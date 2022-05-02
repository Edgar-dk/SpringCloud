package com.sias.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @auther zzyy
 * @create 2020-01-31 12:17
 */
@Configuration
public class ApplicationContextBean
{
    @Bean

    /*01.增加负载均衡，目的使客户端在去
    *    访问服务端的时候，可以按照一定的算法去访问服务端下面的
    *    端口，减少主端口的压力，例如轮询的访问*/
    @LoadBalanced
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
}
 
 

