package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class FeignConfig {

    @Bean
    public Retryer retryer() {

         return Retryer.NEVER_RETRY; //Feign默认值是不走重试策略的
        //最大请求次数为3（1+2: 第一次为默认调用的一次，其它两次是重试机制重试的），初始时间间隔为1000ms，重试最大间隔为1s
//        return new Retryer.Default(100, SECONDS.toMillis(1), 3);
    }

    @Bean
    Logger.Level feignLoggerLevel
            () {
        return Logger.Level.FULL;  // 创建Apache HttpClient 5
    }
}
