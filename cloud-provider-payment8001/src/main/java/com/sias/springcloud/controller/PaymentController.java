package com.sias.springcloud.controller;

import com.sias.springcloud.entities.CommonResult;
import com.sias.springcloud.entities.Payment;
import com.sias.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Edgar
 * @create 2022-04-26 11:17
 * @faction:
 */

@RestController
@Slf4j
public class PaymentController {

    /*01.获取连接对象
    *    然后在下面去使用
    *    这个连接对象里面的类*/
    @Resource
    private PaymentService paymentService;

    /*02.获取Eureka里面的应用个数（就是公司的个数）
    *    也可以获取一个公司里面有几个老师
    *    就是一个application里面有几个服务端口*/
    @Resource
    private DiscoveryClient discoveryClient;
    /*03.标注当前的服务端口是多少，例如这个是8001*/
    @Value("${server.port}")
    private String serverPort;

    /*04.这个放回的结果，是给页面的，是成功了
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
            return new CommonResult(200,"插入数据成功，serverPort："+serverPort,result);
        }else {
           return new CommonResult(444,"插入数据失败",null);
        }
    }

    /*05.读的操作*/
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




    /*06.获取Eureka里面注册的应用和一个应用中的个数*/
    @GetMapping(value = "/payment/discover")
    public Object discover(){
        List<String> services = discoveryClient.getServices();

        for (String service : services) {
            log.info("*****application个数是："+service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("*****端口的信息："+instance.getServiceId()+"\t"+
                    instance.getHost()+"\t"+instance.getPort()+"\t"+
                    instance.getUri());
        }
        return discoveryClient;
    }



    /*07.访问一个地址*/
    @GetMapping(value = "/payment/lb")
    public String getPayment(){
        return serverPort;
    }


    /*08.模拟支付模块花费的时间*/
    @GetMapping(value = "/payment/feign/timeout")
    public String PaymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
