package com.dabing.springcloud.service;

public interface PaymentService {
    public String paymentInfo_OK(Integer id);
    public String payment_Timeout(Integer id);
    public String paymentCircuitBreaker(Integer id);
}
