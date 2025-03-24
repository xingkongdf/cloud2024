package com.atguigu.cloud.mygateway;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 需求说明：自定义配置会员等级 userTpye，按照钻/金/银和 yml 配置的会员等级，以适配是否可以访问
 */
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {
    public static final String USERTYPE_KEY = "userType";
    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(USERTYPE_KEY);
    }


    //config类是我们的路由断言规则，很重要
    @Validated
    public static class Config {

        @Setter@Getter@NotEmpty
        private String userType; //钻 / 金 / 银会员等级

    }
    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //检查request的参数里面，userType是否为指定值，符合配置则通过
                String userTYpe = serverWebExchange.getRequest().getQueryParams().getFirst(USERTYPE_KEY);
                if (config.getUserType().equalsIgnoreCase(userTYpe)){
                    return true;
                }
                return false;
            }
        };
    }
}
