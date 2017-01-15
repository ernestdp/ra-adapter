package com.ernest.reefangel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ernest on 2017/01/14.
 */
@Configuration
public class RAConfig {

@Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();

    }

}
