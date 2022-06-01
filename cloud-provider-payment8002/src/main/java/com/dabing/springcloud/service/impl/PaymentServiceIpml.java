package com.dabing.springcloud.service.impl;

import com.dabing.springcloud.dao.PaymentDao;
import com.dabing.springcloud.entities.Payment;
import com.dabing.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceIpml implements PaymentService {

    @Resource   //@Autowired也可以
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }

}

