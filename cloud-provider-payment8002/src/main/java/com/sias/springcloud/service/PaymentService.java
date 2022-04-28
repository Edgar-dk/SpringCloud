package com.sias.springcloud.service;

import com.sias.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author Edgar
 * @create 2022-04-26 11:13
 * @faction:
 */
public interface PaymentService {

    public int create(Payment payment);

    /*02.查询数据*/
    public Payment getPaymentById(@Param("id") Long id);
}
