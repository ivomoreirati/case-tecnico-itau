package br.com.itau.renegociate.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableRetry
@EnableFeignClients
public class ItauRenegociateBillingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItauRenegociateBillingApplication.class, args);
	}
}
