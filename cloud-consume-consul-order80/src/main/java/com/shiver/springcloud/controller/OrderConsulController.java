package com.shiver.springcloud.controller;

import com.shiver.springcloud.entities.CommentResult;
import com.shiver.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderConsulController {

    public static final String PAYMENT_URL="http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/payment/consul")
    public String create(Payment payment){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/consul",String.class);
    }
}
