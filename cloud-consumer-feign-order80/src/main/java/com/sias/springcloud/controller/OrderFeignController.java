package com.sias.springcloud.controller;

import com.sias.springcloud.entities.CommonResult;
import com.sias.springcloud.entities.Payment;
import com.sias.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Edgar
 * @create 2022-05-02 17:01
 * @faction:
 */

@RestController
public class OrderFeignController {



    @Resource
    private PaymentFeignService paymentFeignService;


    /*01.先去访问，这个地址，然后在根据这个地址，在去
    *    跳转到其他的地址，做了这么多的代码，可能会产生一个问题
    *    首先，下面的代码和支付模块中的代码几乎完全一样，这个只是一个
    *    测试，其实在真正的业务逻辑阶段，还需要更好多的代码，这个层的
    *    去调用这层的，一个例如，这个层是选择了一个外卖，这层是
    *    选择外卖的过程，这个过程是一步一步的，但是到了真正的支付钱的
    *    一步，还是需要转到支付的地方的，这个转的过程，还是需要Http起着至关重要
    *    的一步，这个支付模块的页面是在这个模块上看到的，因此在启动的时候
    *    需要做好二者的联系，从里面扣钱*/
    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> PaymentService(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }


    /*02.模拟支付模块花费的时间
    *
    *    这个是消费者的地址*/
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String PaymentFeignTimeout(){
        return paymentFeignService.PaymentFeignTimeout();
    }
}
