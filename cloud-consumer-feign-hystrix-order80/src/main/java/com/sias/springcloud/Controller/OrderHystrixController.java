package com.sias.springcloud.Controller;

import com.sias.springcloud.Service.PaymentHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Edgar
 * @create 2022-05-03 19:35
 * @faction:
 */

@RestController
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService orderHystrixService;

    /*01.完成的代码*/
    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String PaymentInfoOk(@PathVariable("id") Integer id){
        String infoOk = orderHystrixService.PaymentInfoOk(id);
        return infoOk;
    }

    /*02.超时的代码*/
    @GetMapping(value = "/consumer/payment/hystrix/Timeout/{id}")
    public String PaymentHystrixTimeout(@PathVariable("id") Integer id){
        String timeout = orderHystrixService.PaymentHystrixTimeout(id);
        return timeout;
    }




}
