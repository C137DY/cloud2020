package com.shiver.springcloud.controller;

import com.shiver.springcloud.entities.CommentResult;
import com.shiver.springcloud.entities.Payment;
import com.shiver.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping(value = "/payment/create")
    public CommentResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        System.out.println("*****结果=="+result);
        if (result>0){
            return new CommentResult(200,"插入数据成功"+port,result);
        }
        return new CommentResult(444,"插入数据失败",null);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommentResult create(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        System.out.println("*****结果=="+payment);
        if (payment != null){
            return new CommentResult(200,"查询成功"+port,payment);
        }
        return new CommentResult(444,"没有对应的记录",null);
    }

    @GetMapping("payment/lb")
    public String getLB(){
        return port;
    }
}
