package com.sias.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Edgar
 * @create 2022-04-30 11:13
 * @faction:
 */

@RestController
@Slf4j
public class PaymentController {


    @Value("${server.port}")
    private String serverPort;


    /*这个是一个测试方法
    * 目的是为了测试和虚拟机上的服务器是否
    * 可以连接上*/
    @RequestMapping(value = "/payment/zk")
    public String paymentzk()
    {
        return "springcloud with zookeeper: "+serverPort+"\t"+ UUID.randomUUID().toString();
    }

}
