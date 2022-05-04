package com.sias.springcloud.controller;

import com.sias.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Edgar
 * @create 2022-05-03 10:43
 * @faction:
 */


@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    /*01.ok的访问*/
    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String PaymentInfoOk(@PathVariable("id") Integer id){
        String infoOk = paymentService.PaymentInfoOk(id);
        log.info("成功的展示了:"+infoOk);
        return infoOk;
    }

    /*02.超时的访问*/
    @GetMapping(value = "/payment/hystrix/Timeout/{id}")
    public String PaymentHystrixTimeout(@PathVariable("id") Integer id){
        String time = paymentService.PaymentInfoTime(id);
        log.info("超时的演示:"+time);
        return time;
    }


    /*03.熔断机制*/
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("****result: "+result);
        return result;
    }
}
