package com.ernest.reefangel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ernest on 2017/01/14.
 */
@Configuration
@Import({SwaggerConfig.class, DBConfig.class})
public class RAConfig {

    @Value("${slackWebApi}")
    String slackApi;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplate slackApiTemplate(RestTemplateBuilder builder)
    {
        builder.rootUri(slackApi);
        return builder.build();
    }
}
