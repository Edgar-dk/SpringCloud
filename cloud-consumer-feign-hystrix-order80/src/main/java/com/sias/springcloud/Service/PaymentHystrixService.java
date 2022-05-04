package com.sias.springcloud.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Edgar
 * @create 2022-05-03 19:37
 * @faction:
 */

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class/*
正常的逻辑是，按照前面的服务名称，去访问对应的端口号，但是对面的服务器宕机的话，需要在消费者端做好服务降级
（就是写好备案方案，不让消费端访问的时候，一直处于转圈的状态），这个备案方案就是fallback，对方宕机的话
去访问这个类中对应的方法*/)
public interface PaymentHystrixService {

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String PaymentInfoOk(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/Timeout/{id}")
    public String PaymentHystrixTimeout(@PathVariable("id") Integer id);
}

