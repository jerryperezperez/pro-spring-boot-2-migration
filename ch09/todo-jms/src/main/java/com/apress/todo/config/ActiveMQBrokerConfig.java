package com.apress.todo.config;


import org.apache.activemq.broker.BrokerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActiveMQBrokerConfig {

    @Bean
    public BrokerService broker() throws Exception {
        BrokerService broker = new BrokerService();
        broker.setBrokerName("inMemoryBroker-broker");
        broker.setPersistent(false); // In-memory broker
        broker.setUseJmx(true);      // Enable JMX for monitoring
        broker.addConnector("vm://localhost");
        broker.start();
        return broker;
    }
}

