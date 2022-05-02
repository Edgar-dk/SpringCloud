package com.sias.springcloud.service;

import com.sias.springcloud.entities.CommonResult;
import com.sias.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Edgar
 * @create 2022-05-02 16:18
 * @faction:
 */

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService
{

    /*01.按照id去查询
    *    一开始的时候，需要在需要在消费者哪里
    *    去使用一个，RestTemplate和支付模块连接
    *    并且在连接的时候，还要使用上这个对象，
    *    现在直接使用这个，就可以使消费者和注册中心
    *    连接起来，然后在去访问连接中心的一个支付模块
    *    至于这个模块的端口号的访问，可以使用默认的
    *    轮询算法，也可以去使用其他的算法（需要个人去设置）
    *    因为这个底层封装了Ribbon，所以是可以使用的
    *
    *    这个可以理解成，我想要吃的东西（消费者），这个东西，
    *    需要在我的脑子中存在，存在的东西的意念使得我
    *    走向了卖这个吃的东西的商店（支付模块），然后我想的
    *    正好商店（支付模块）有，所以可以理解成是一一对应上了
    * */
    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);



    /*02.模拟支付模块花费的时间
    *
    *    这个是支付模块的地址*/
    @GetMapping(value = "/payment/feign/timeout")
    String PaymentFeignTimeout();
}
