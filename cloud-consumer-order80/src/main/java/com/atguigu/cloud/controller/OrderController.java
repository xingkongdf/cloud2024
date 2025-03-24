package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrderController {
//    public static final String PaymentSrv_url = "http://localhost:8001"; //先写死，硬编码
    public static final String PaymentSrv_url = "http://cloud-payment-service"; //服务注册中心上的微服务名称

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO) {
        return restTemplate.postForObject(String.format("%s%s",PaymentSrv_url,"/pay/add"), payDTO, ResultData.class);
    }

    @GetMapping("/consumer/pay/getById/{id}")
    public ResultData getOrder(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(String.format("%s%s/%s",PaymentSrv_url,"/pay/getById",id), ResultData.class);
    }

    @GetMapping("/consumer/pay/del/{id}")
    public ResultData delOrder(@PathVariable("id") Integer id) {
         restTemplate.delete(String.format("%s%s/%s",PaymentSrv_url,"/pay/del",id));
         return ResultData.success("删除成功");
    }

    @GetMapping("/consumer/pay/update")
    public ResultData updateOrder(PayDTO payDTO) {
        restTemplate.put(String.format("%s%s",PaymentSrv_url,"/pay/update"), payDTO);
        return ResultData.success("更新成功");
    }

    @GetMapping("/consumer/pay/get/info")
    public String getInfoByConsul()
    {
        return restTemplate.getForObject(PaymentSrv_url+"/pay/get/info", String.class);
    }

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/discovery")
    public String discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element: services){
            System.out.println(element);
        }
        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance: instances){
            System.out.println(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return instances.get(0).getHost()+":"+instances.get(0).getPort();
    }
}
