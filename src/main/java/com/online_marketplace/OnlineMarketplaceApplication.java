package com.online_marketplace;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;



@SpringBootApplication
@EntityScan("com.online_marketplace.model")
public class OnlineMarketplaceApplication{

	public static void main(String[] args) {
		SpringApplication.run(OnlineMarketplaceApplication.class, args);
	}
}
