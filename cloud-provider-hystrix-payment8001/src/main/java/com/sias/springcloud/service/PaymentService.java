package com.sias.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sias.springcloud.entities.SleepUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Edgar
 * @create 2022-05-03 10:36
 * @faction:
 */

/*注意：这个Service可以是一个接口，也可以是一个类，用法都是一样的*/


@Service
public class PaymentService {


    /*01.这个方法是OK的*/
    public String PaymentInfoOk(Integer id){
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_ok，id:"+id+"\t"+"成功了";
    }


    /*02.设置一定的时间在去执行
    *
    *    分析，当这个方法执行时间超过了执行的时间
    *    就要去执行兜底的方法，这个方法的功能可能看起
    *    来不是很强大，是为了把这个服务转移，不让消费者
    *    看到页面出错的内
    *    第二个参数是，设置的最大时间，超过了这个时间的话
    *    去按照第一个参数的名字去找到对应的方法执行*/
    @HystrixCommand(fallbackMethod = "PaymentInfoTimeHandler",
            commandProperties = {@HystrixProperty(name = "execution.isolation.th" +
                    "read.timeoutInMilliseconds",value="3000")
    })
    public String PaymentInfoTime(Integer id){
        int i=10/0;
//        SleepUtils.sleep(5);
        return "线程池:"+Thread.currentThread().getName()+"paymentInfoTime，id:"+id+"\t"+"成功展示";
    }


    public String PaymentInfoTimeHandler(Integer id){
        return "线程池:"+Thread.currentThread().getName()+"paymentInfoTime，id:"+id+"\t"+"这个是执行失败的方法";
    }
}
