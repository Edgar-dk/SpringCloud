package com.sias.springcloud.controller;



import com.sias.springcloud.entities.CommonResult;
import com.sias.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



import javax.annotation.Resource;

/**
 * @author Edgar
 * @create 2022-04-27 17:31
 * @faction:
 */

@RestController
@Slf4j
public class OrderController {
    /*01.公共的地址*/
    public static final String PAYMENT_URL = "http://localhost:8001";
    @Resource
    private RestTemplate restTemplate;
    /*02.写的操作
    *
    *    Payment对象插入数据
    *    返回的数据，也是对象，是另外一个对象，
    *    返回的数据里面的类型是传递数据的类型
    *
    *    方法中的后两个参数是，在调用结束之后，在返回到这个页面*/
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment)
    {
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create/",payment, CommonResult.class);
    }
    /*03.读的操作*/
    @GetMapping("/consumer/payment/get{id}")
    public CommonResult<Payment> getPayment1(@PathVariable("id") Long id)
    {
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }


}
