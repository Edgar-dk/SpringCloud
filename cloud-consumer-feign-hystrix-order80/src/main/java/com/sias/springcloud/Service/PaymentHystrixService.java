package com.sias.springcloud.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Edgar
 * @create 2022-05-03 19:37
 * @faction:
 */

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT")
public interface PaymentHystrixService {

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String PaymentInfoOk(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/Timeout/{id}")
    public String PaymentHystrixTimeout(@PathVariable("id") Integer id);
}

