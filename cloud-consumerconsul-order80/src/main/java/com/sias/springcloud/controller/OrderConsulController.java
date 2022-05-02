package com.sias.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @auther zzyy
 * @create 2020-01-31 12:18
 */
@RestController
public class OrderConsulController
{

    /*01.这个是访问支付模块的地址
    *    因为这个支付模块已经注入进了consul
    *    所以是可以直接去访问这个模块的名字的
    *    然后，访问到了模块的名字之后，在按照
    *    一定的算法去访问对应的端口号*/
    public static final String INVOKE_URL = "http://consul-provider-payment"; //consul-provider-payment

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/consul")
    public String paymentInfo()
    {
        String result = restTemplate.getForObject(INVOKE_URL+"/payment/consul", String.class);
        System.out.println("消费者调用支付服务(consule)--->result:" + result);
        return result;
    }
}
 
 
 

