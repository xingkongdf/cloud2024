package com.atguigu.cloud.controller;

import com.atguigu.cloud.service.FlowLimitService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "-----testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "-----testB";
    }

    /**
     * 流控-链路演示demo
     * C和D两个请求都访问 flowLimitService.common()方法，阈值达到对C限流，对D不管
     */

    @Resource
    private FlowLimitService flowLimitService;

    @GetMapping("/testC")
    public String testC(){
        flowLimitService.common();
        return "-----testC";
    }

    @GetMapping("/testD")
    public String testD(){
        flowLimitService.common();
        return "-----testD";
    }

    /**
     * 流控效果排队等待
     * @return
     */
    @GetMapping("/testE")
    public String testE() {
        System.out.println(System.currentTimeMillis() + "      testE,排队等待");
        return "------testE";
    }

    /**
     * 新增熔断规则，慢调用比例
     * @return
     */
    @GetMapping("/testF")
    public String testF() {
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){}
        System.out.println("测试：新增熔断规则-慢调用比例");
        return "------testF 新增熔断规则-慢调用比例";
    }

    /**
     * 新增熔断规则-异常比例
     * @return
     */
    @GetMapping("/testG")
    public String testG() {
        System.out.println("测试：新增熔断规则-异常比例");
        int i=10/0;
        return "------testG 新增熔断规则-异常比例";
    }

    /**
     * 新增熔断规则-异常数
     * @return
     */
    @GetMapping("/testH")
    public String testH() {
        System.out.println("测试：新增熔断规则-异常数");
        int i=10/0;
        return "------testG 新增熔断规则-异常数";
    }
}
