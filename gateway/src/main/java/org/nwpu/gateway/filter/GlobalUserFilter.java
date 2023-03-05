package org.nwpu.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 用户请求全局过滤器
 * @author lzy
 */
@Slf4j
@Component
public class GlobalUserFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String sourceAddress = request.getLocalAddress().getHostString();
        log.info("请求来源地址ip " + sourceAddress);
        log.info("请求来源地址 " + request.getRemoteAddress());
        log.info("请求类型 " + request.getMethod());
        log.info("请求地址 " + request.getPath());
        log.info("请求参数 " + request.getQueryParams());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
