package com.green.groupirum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GroupirumApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupirumApplication.class, args);
	}

}
