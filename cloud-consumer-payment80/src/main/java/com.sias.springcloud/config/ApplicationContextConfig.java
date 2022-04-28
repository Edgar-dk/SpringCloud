package com.sias.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Edgar
 * @create 2022-04-27 17:34
 * @faction:
 */


/*这个是一个配置类，相当于spring中的bean容器*/
@Configuration
public class ApplicationContextConfig {


    /*这个类的目的是为了让这个80端口和8001端口相连接
    * 可以直接在Controller层使用*/
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
