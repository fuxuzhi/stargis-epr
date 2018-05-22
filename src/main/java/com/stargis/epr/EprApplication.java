package com.stargis.epr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.stargis.epr.dao")
public class EprApplication {
	public static void main(String[] args) {
		System.out.println("=================EprApplication=====================");
		SpringApplication.run(EprApplication.class, args);
	}
}
