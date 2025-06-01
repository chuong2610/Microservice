package com.cybersoft.bookshop_authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class BookshopAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookshopAuthenticationApplication.class, args);
	}

}
