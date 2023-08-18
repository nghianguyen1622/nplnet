package com.npl.global;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class NplApplication {

	public static void main(String[] args) {
		SpringApplication.run(NplApplication.class, args);
	}

}
