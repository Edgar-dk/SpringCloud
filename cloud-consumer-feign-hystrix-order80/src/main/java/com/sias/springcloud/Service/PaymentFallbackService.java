package com.sias.springcloud.Service;

import org.springframework.stereotype.Component;

/**
 * @author Edgar
 * @create 2022-05-04 15:31
 * @faction:
 */

/*当对面的服务器出现了重大的问题，业务都导致不可以访问了，可以
访问这个消费端的备案方案*/
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String PaymentInfoOk(Integer id) {
        return "PaymentInfoOk-对方服务器宕机了";
    }

    @Override
    public String PaymentHystrixTimeout(Integer id) {
        return "PaymentHystrixTimeout-对方服务器超时";
    }
}
