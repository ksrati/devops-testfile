package com.customer.customerManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.vault.config.VaultAutoConfiguration;

@EnableAutoConfiguration(exclude = VaultAutoConfiguration.class)

@SpringBootApplication
public class CustomerManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerManagementApplication.class, args);
	}

}
