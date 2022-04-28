package com.sias.springcloud.controller;

import com.sias.springcloud.entities.CommonResult;
import com.sias.springcloud.entities.Payment;
import com.sias.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Edgar
 * @create 2022-04-26 11:17
 * @faction:
 */

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;


    /*当有多个服务的话需要标明哪一个是主服务*/
    @Value("$(server.port)")
    private String serverPort;

    /*01.这个放回的结果，是给页面的，是成功了
    *    还是失败了，返回的是一个标识符（0/1）
    *
    *    需要注意一点的是，ResponseBody是转换对象中的数据
    *    转换成Json，然后在放回到页面上显示，对于页面上来的数据
    *    需要把Json或者是xml数据转换成对象，在存放到数据库中*/
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if (result > 0){
            /*02.可以在这个一步分开去写，先set在去传递这个对象*/
            return new CommonResult(200,"插入数据成功:serverPort"+serverPort,result);
        }else {
           return new CommonResult(444,"插入数据失败",null);
        }
    }


    @GetMapping (value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
//        log.info("这个是一个启动程序："+payment);
        if (payment !=null){
            /*02.可以在这个一步分开去写，先set在去传递这个对象
            *    这个返回的是一个对象，可以写创建好的对象的名字
            *    同时也可以写，直接new好的对象，原理都是一样的
            *    返回里面设置好的值即可，这个值存放在对象里
            *    因此在返回数据类型的时候，还是按照存放的这个
            *    类型去放回，把数据转换成json字符串给访问者
            *
            *    至于数据在放回的时候发生了什么，首先return后面的
            *    数据按照这个方法定义的类型返回，return后数据先到，
            *    方法的类型上，看看return后面的数据，是否还需要和
            *    这个类型去进一步的处理，从下面可以看出，是需要进一
            *    步处理的，把从数据库中获取的数据，按照这个类型去显示
            *    然后在一并放到一个大的字符串中，最终以json的形式
            *    把数据展示在页面上*/
            return new CommonResult(200,"查询数据成功:serverPort"+serverPort,payment);
        }else {
            return new CommonResult(444,"查询数据失败:"+id,null);
        }
    }


    /*测试跳转页面*//*
    @RequestMapping( "/success")
    @ResponseBody
    public String success(){
        return "main";
    }*/
}
