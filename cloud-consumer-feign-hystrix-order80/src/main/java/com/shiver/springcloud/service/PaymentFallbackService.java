package com.shiver.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_ok(Integer id) {
        return "服务异常";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "连接超时";
    }
}
