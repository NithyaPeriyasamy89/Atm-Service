package com.atm.AtmMachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.atm"})
@EnableFeignClients(basePackages = {"com.atm.service"})
public class AtmMachineApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmMachineApplication.class, args);
	}

}
