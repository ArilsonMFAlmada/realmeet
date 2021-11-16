package br.com.sw2you.realmeet.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorsConfiguration {

    @Bean
    public Executor controllersExecution(
            @Value("${realmeet.taskExecutor.pool.coreSize:20}") int corePoolSize,
            @Value("${realmeet.taskExecutor.pool.maxSPoolize:20}")int maxPoolSize,
            @Value("${realmeet.taskExecutor.pool.queueCapacity:50}")int queueCapacity,
            @Value("${realmeet.taskExecutor.pool.keepAliveSeconds:60}")int keepAliveSeconds
    ) {
        return new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveSeconds,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueCapacity,true));
    }
}
