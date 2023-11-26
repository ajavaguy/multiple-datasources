package com.example.multipledatasources.infrastructure.circuitbreaker;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.internal.InMemoryCircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfiguration {

    @Bean
    public CircuitBreakerRegistry registry() {
        InMemoryCircuitBreakerRegistry circuitBreakerRegistry = new InMemoryCircuitBreakerRegistry();

        circuitBreakerRegistry.circuitBreaker(CircuitBreakerName.GET_ADDRESS_BY_EMPLOYEE_ID, getAddressByEmployeeId());

        circuitBreakerRegistry.getAllCircuitBreakers()
                .forEach(circuitBreaker -> circuitBreaker
                        .getEventPublisher()
                        .onStateTransition(event -> System.out.println(event.toString())));
        return circuitBreakerRegistry;
    }

    private CircuitBreakerConfig getAddressByEmployeeId() {
        return restApiDefaultConfig()
                .build();
    }

    private CircuitBreakerConfig.Builder restApiDefaultConfig() {
        return defaultConfig().ignoreExceptions(HttpClientErrorException.class);
    }

    private CircuitBreakerConfig.Builder defaultConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(100)
                .slowCallRateThreshold(100)
                .slowCallDurationThreshold(Duration.ofSeconds(3))
                .minimumNumberOfCalls(1)
                .slidingWindowSize(1)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .waitDurationInOpenState(Duration.ofSeconds(15))
                .permittedNumberOfCallsInHalfOpenState(10)
                .automaticTransitionFromOpenToHalfOpenEnabled(true);
    }
}
