package com.sias.springcloud.service.impl;

import com.sias.springcloud.dao.PaymentDao;
import com.sias.springcloud.entities.Payment;
import com.sias.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Edgar
 * @create 2022-04-26 11:14
 * @faction:
 */


@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;


    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
