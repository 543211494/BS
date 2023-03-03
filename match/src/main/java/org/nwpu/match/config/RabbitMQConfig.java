package org.nwpu.match.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置类
 */
@Configuration
public class RabbitMQConfig {

    /**
     * 交换机名称
     */
    public final static String EXCHANGE_NAME = "direct-exchange";

    /**
     * 消息队列名称
     */
    public final static String QUEUE_NAME = "mathc-queue";

    /**
     * 路由关键词
     */
    public final static String ROUTING_KEY = "match";

    /**
     * 声明交换机
     * @return
     */
    @Bean("bootExchange")
    public Exchange bootExchange(){
        return ExchangeBuilder.directExchange(EXCHANGE_NAME).durable(false).build();
    }

    /**
     * 声明队列
     * @return
     */
    @Bean("bootQueue")
    public Queue bootQueue(){
        return QueueBuilder.nonDurable(QUEUE_NAME).build();
        //return QueueBuilder.durable(QUEUE_NAME).build();
    }

    /**
     * 绑定交换机和队列
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindQueueExchange(@Qualifier("bootQueue") Queue queue,@Qualifier("bootExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY).noargs();
    }

}
