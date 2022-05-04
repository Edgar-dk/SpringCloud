package com.sias.springcloud.Controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod"/*
作用：下面如果使用到了HystrixCommand这个注解的话，可以统一的给下面的方法增加一个兜底的方法
这些演示的都是服务降级*/)
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
    /*@GetMapping(value = "/consumer/payment/hystrix/Timeout/{id}")
    public String PaymentHystrixTimeout(@PathVariable("id") Integer id){
        String timeout = orderHystrixService.PaymentHystrixTimeout(id);
        return timeout;
    }*/


    /*03.超时代码的演示
    *
    *    第二个参数中的数据表示，当支付模块超过这个时间的话
    *    消费端，就不去等待支付模块放回数据，直接走自己的兜底方案
    *    兜底的方案在下面,当自身的代码出现了问题的时候，也会走兜底
    *    的方案，在使用了统一的兜底方法的时候，需要把这个兜底方法注释掉
    *    这些演示的是服务降级*/
    @GetMapping("/consumer/payment/hystrix/Timeout/{id}")
    @HystrixCommand/*(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
    })*/
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id)
    {
        int i =10/0;
        String result = orderHystrixService.PaymentHystrixTimeout(id);
        return result;
    }
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id)
    {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }


    /*04.这是统一兜底的方法
    *
    *    服务降级的调用方法*/
    public String payment_Global_FallbackMethod()
    {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }


}
