package com.shiver.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shiver.springcloud.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "paymentInfo_timeoutHandler")  //全局的服务降级处理
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_ok(id);
    }

    @GetMapping("/consumer/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
//    })
    @HystrixCommand
    public String paymentInfo_timeout(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_timeout(id);
    }

    public String paymentInfo_timeoutHandler(@PathVariable("id") Integer id){
        return "消费者80,对方支付系统繁忙";
    }
}
