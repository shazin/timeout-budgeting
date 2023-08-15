package com.medium.shazinsadakath.timeout.budgeting.config;

//import com.medium.shazinsadakath.timeout.budgeting.utils.TimeoutBudgetRestTemplateInterceptor;
import com.medium.shazinsadakath.timeout.budgeting.utils.TimeoutBudgetRestTemplateInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
@EnableRetry
public class AppConfig {

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        return retryTemplate;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Arrays.asList(new TimeoutBudgetRestTemplateInterceptor()));

        return restTemplate;
    }

}
