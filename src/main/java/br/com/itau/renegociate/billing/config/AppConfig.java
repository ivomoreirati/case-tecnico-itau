package br.com.itau.renegociate.billing.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Single thread for manually triggered jbos
     * @return Single thread ExecutorService
     */
    @Bean
    public ExecutorService executorService() {
        return Executors.newSingleThreadExecutor();
    }
}
