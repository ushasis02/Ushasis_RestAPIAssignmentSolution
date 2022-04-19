package com.gl.EmpMgm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;




@SpringBootApplication
@EnableWebSecurity
public class EmpMgmApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EmpMgmApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Hello Springboot");	
	}

}
