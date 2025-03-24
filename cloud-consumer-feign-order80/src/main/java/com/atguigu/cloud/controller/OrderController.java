package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.atguigu.cloud.resp.ReturnCodeEnum.RC500;

@RestController
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping("/feign/pay/getById/{id}")
    public ResultData getOrder(@PathVariable("id") Integer id) {
        ResultData resultData = new ResultData();
        try{
            System.out.println("调用开始----: "+DateUtil.now());
            resultData = payFeignApi.getPayInfo(id);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("调用结束----: "+DateUtil.now());
            ResultData.fail(RC500.getCode(),e.getMessage());
        }
        System.out.println("调用结束----: "+DateUtil.now());
        return resultData;
    }


    @GetMapping("/feign/pay/mylb")
    public String getInfoByConsul()
    {
        return payFeignApi.mlb();
    }



}
