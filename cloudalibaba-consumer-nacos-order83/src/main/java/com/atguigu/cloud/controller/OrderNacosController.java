package com.atguigu.cloud.controller;

import com.atguigu.cloud.apis.PayFeignSentinelApi;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderNacosController {
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @GetMapping("/consumer/pay/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Integer id){
        String result = restTemplate.getForObject(serverUrl+"/pay/nocas/"+id,String.class);
        return result+"\t"+"  我是OrderNacosController调用者。。。。";
    }

    @Resource
    private PayFeignSentinelApi payFeignSentinelApi;
    @GetMapping("/consumer/pay/nacos/get/{orderNo}")
    public ResultData getPayByOrderNo(@PathVariable("orderNo") String orderNo){
        return payFeignSentinelApi.getPayByOrderNo(orderNo);
    }

}
