package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.mapper.AccountMapper;
import com.atguigu.cloud.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;
    @Override
    public void decrease(Long userId, Long money) {
       log.info("=====>扣减账户余额，AccountServiceImpl： "+userId);
       accountMapper.decrease(userId, money);
//       myTimeOut();
//       int age=10/0;
        log.info("=====>扣减账户余额完成，AccountServiceImpl： "+userId);

    }

    /**
     模拟超时异常，全局事务回滚
     */
    private static void myTimeOut ()
    {
        try {
            TimeUnit.SECONDS.sleep (65); } catch (InterruptedException e) { e.printStackTrace (); }
    }
}
