package com.dabing.springcloud.controller;

import com.dabing.springcloud.entities.CommonResult;
import com.dabing.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;  //调用远程的微服接口

    @GetMapping(value = "/consumer/openFeign/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
       return paymentFeignService.getPaymentById(id);
    }


    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        return paymentFeignService.paymentFeignTimeout();
    }
}
