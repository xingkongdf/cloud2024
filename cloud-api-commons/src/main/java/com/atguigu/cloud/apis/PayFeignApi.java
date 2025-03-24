package com.atguigu.cloud.apis;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//@FeignClient("cloud-payment-service")
@FeignClient("cloud-gateway")
public interface PayFeignApi {

    /**
     * 添加一条记录
     * @param payDTO
     * @return
     */
    @PostMapping("/pay/add")
    public ResultData  addPay(@RequestBody PayDTO payDTO);

    /***
     * 根据id查询一条记录
     * @param id
     * @return
     */
    @GetMapping("/pay/getById/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id);

    /***
     * openfeign天然支持负载均衡演示
     * @return
     */
    @GetMapping("/pay/get/info")
    public String mlb();

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    @DeleteMapping("/pay/del/{id}")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id);

    /**
     * 根据id更新信息
     * @param payDTO
     * @return
     */
    @PutMapping("/pay/update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO);

    //=====resilience4j-circuitbreaker例子
    @GetMapping("/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id") Integer id);

    /**
     * Resilience4j Bulkhead 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    public String myBulkhead(@PathVariable("id") Integer id);

    /**
     * Resilience4j myRatelimit 的例子
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/myRatelimit/{id}")
    public String myRatelimit(@PathVariable("id") Integer id);

    /**
     * Micrometer监控案例
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    public String myMicrometer(@PathVariable("id") Integer id);

    /**
     * Gateway进行网关测试案例01
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/gateway/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id);

    /**
     * Gateway进行网关测试案例02
     * @return
     */
    @GetMapping(value = "/pay/gateway/info")
    public ResultData<String> getGatewayInfo();

}
