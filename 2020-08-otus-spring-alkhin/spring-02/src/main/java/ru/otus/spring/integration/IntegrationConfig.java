package ru.otus.spring.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;

@Configuration
public class IntegrationConfig {

    @Bean
    DirectChannel outputChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow saveBookFlow() {
        return flow -> flow
                .handle("bookService", "saveBook")
                .channel("outputChannel");
    }


    @Bean
    public IntegrationFlow getBookById() {
        return flow -> flow
                .handle("bookService", "getBookById")
                .channel("outputChannel");
    }

}
