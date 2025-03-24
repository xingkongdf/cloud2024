package com.atguigu.cloud.mygateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter , Ordered {
    private static String BEGIN_VISIT_TIME = "beginVisitTime";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(BEGIN_VISIT_TIME, System.currentTimeMillis());

        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            Long attribute = exchange.getAttribute(BEGIN_VISIT_TIME);
            if (attribute != null) {
                log.info("访问接口主机："+exchange.getRequest().getURI().getHost());
                log.info("访问接口端口："+exchange.getRequest().getURI().getPort());
                log.info("访问接口URL："+exchange.getRequest().getURI().getPath());
                log.info("访问接口参数："+exchange.getRequest().getURI().getQuery());
                log.info("访问接口时长："+(System.currentTimeMillis()-attribute)+"毫秒");
                log.info("===========================分割线==================");
                System.out.println();
            }
        }));
    }

    //数字越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
