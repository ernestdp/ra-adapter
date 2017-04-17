package com.ernest.reefangel;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ernest on 2017/01/14.
 */
@Configuration
@Import({SwaggerConfig.class, DBConfig.class})
public class RAConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
