package com.shiver.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shiver.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Override
    public String paymentInfo_ok(Integer id) {
        return "线程池:    "+Thread.currentThread().getName()+"    paymentInfo_ok";
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_timeout(Integer id) {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:    "+Thread.currentThread().getName()+"    paymentInfo_timeout";
    }

    public String paymentInfo_timeoutHandler(Integer id){
        return "线程池:    "+Thread.currentThread().getName()+"    paymentInfo_timeout+ZZZZZZZZZZ";
    }
}
