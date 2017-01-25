package com.ernest.reefangel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"me.ramswaroop.jbot","com.ernest.reefangel"})
public class RaAdapterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RaAdapterApplication.class, args);
	}
}
