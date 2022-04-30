package com.sias.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Edgar
 * @create 2022-04-30 19:33
 * @faction:
 */

@RestController
@Slf4j
public class OrderZKController {


    public static final String INVOKE_URL="http://cloud-provider-payment";


    /*01.注意:想要使得，消费者和支付模块去连接，还是需要支付模块的地址的
    * 并且需要一个类来嫁接中间的连接*/
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping(value = "/consumer/payment/zk")
    public String paymentInfo(){
        /*02.最后的一个返回值，是调用支付模块之后，把产生的数据按照这个类型
             放回了过来，然后直接，按照这个产生的数据类型接收数据即可*/
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
        /*03.下面的这个放回值，直接把产生的数据放回调用者（页面）转化成json
        *    字符串形式的数据*/
        return result;
    }
}
