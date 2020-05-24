package com.shiver.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 新建的ribbon算法不能被@ScanCompent 扫描
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        //随机算法
        System.out.println("*****RandomRule*****");
        return new RandomRule();
    }
}
