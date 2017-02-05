package com.ernest.reefangel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@SpringBootApplication(scanBasePackages = {"me.ramswaroop.jbot","com.ernest.reefangel"})
public class RaAdapterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RaAdapterApplication.class, args);
	}

}
