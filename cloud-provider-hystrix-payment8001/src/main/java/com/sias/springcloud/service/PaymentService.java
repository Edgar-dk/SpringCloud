package com.sias.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sias.springcloud.entities.SleepUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author Edgar
 * @create 2022-05-03 10:36
 * @faction:
 */

/*注意：这个Service可以是一个接口，也可以是一个类，用法都是一样的*/


@Service
public class PaymentService {

    /*服务降级
    * 当调用的某一个节点出现了微小的问题，会出现服务降级
    * 为了防止这个点出现完全的宕机，或者是宕机
    * 而影响整体，可以提前的做服务熔断，把这个服务断掉（用户不去访问）
    * 过了一段时间在去访问的话，成功的话，在去开启*/

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
    *    去按照第一个参数的名字去找到对应的方法执行
    *    当商是0的时候，这个时候，会报错走的方案，也是按照
    *    第一个参数的名字去查找对应的方法*/
    @HystrixCommand(fallbackMethod = "PaymentInfoTimeHandler",
            commandProperties = {@HystrixProperty(name = "execution.isolation.th" +
                    "read.timeoutInMilliseconds",value="5000")
    })
    public String PaymentInfoTime(Integer id){
        //int i=10/0;
        SleepUtils.sleep(3);
        return "线程池:"+Thread.currentThread().getName()+"paymentInfoTime，id:"+id+"\t"+"成功展示";
    }


    /*03.作为上一个方法的另一个方案*/
    public String PaymentInfoTimeHandler(Integer id){
        return "线程池:"+Thread.currentThread().getName()+"paymentInfoTime，id:"+id+"\t"+"这个是执行失败的方法";
    }







    /*=====服务熔断
    *
    *      下面的参数需要联合在一起使用，才可以更好的发挥熔断机制
    *      分析：需要开启熔断机制（相当于有了保险丝）只有触发了条件
    *      之后，才可以开启这个熔断，熔断的是这个服务，不在访问这个点的
    *      服务，之后达到了一定的条件之后，才可以开启，开启这个熔断的条件
    *      按照后三个参数分析，在规定的时间内，完成规定次数的访问，这个访问
    *      的失败率达到规定的数目，这些条件在规定的时间内完成，才会开启熔断机制
    *      然后在正确的访问，在规定的时间内，正确率超过了错误率，关闭熔断机制*/
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//开启熔断机制
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求的次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少次后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }

}
