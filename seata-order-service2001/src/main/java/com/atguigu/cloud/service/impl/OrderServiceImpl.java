package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.apis.AccountFeignApi;
import com.atguigu.cloud.apis.StorageFeignApi;
import com.atguigu.cloud.entities.Order;
import com.atguigu.cloud.mapper.OrderMapper;
import com.atguigu.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StorageFeignApi storageFeignApi; //订单服务通过OpenFeign去调用库存微服务

    @Resource
    private AccountFeignApi accountFeignApi; //订单服务通过OpenFeign去调用账户微服务


    @Override
    @GlobalTransactional(name="wj-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        //全局事务id的检查，重要
        String xid = RootContext.getXID();
        //1、新建订单
        log.info("------------------开始新建订单："+"\t"+"xid: "+xid);
        //订单新建时默认状态为0
        order.setStatus(0);
        int result = orderMapper.insertSelective(order);
        if (result > 0) {
            Order orderFromDB = orderMapper.selectOne(order);
            log.info("------------------新建订单成功，orderFromDB： "+orderFromDB);
            System.out.println();
            //扣减库存
            log.info("-----------------调用订单微服务扣减库存，做扣减count");
            storageFeignApi.decrease(orderFromDB.getProductId(),orderFromDB.getCount());
            log.info("-----------------订单微服务结束扣减库存，做扣减count完成");
            System.out.println();
            //扣减账号余额
            log.info("-----------------调用账户微服务扣减余额，做扣减money");
            accountFeignApi.decrease(orderFromDB.getUserId(),orderFromDB.getMoney());
            log.info("-----------------调用账户微服务扣减余额完成，做扣减money完成");
            System.out.println();
            //修改订单状态
            //将订单状态从0修改为1
            log.info("-----------------订单状态修改");
            orderFromDB.setStatus(1);
            int i = orderMapper.updateByPrimaryKeySelective(orderFromDB);
            log.info("-----------------订单状态修改完成"+"\t"+i);
            System.out.println();
            log.info("----> orderFromDB info: "+orderFromDB);

        }
        System.out.println();
        log.info("------------------结束新建订单："+"\t"+"xid: "+xid);

    }
}
