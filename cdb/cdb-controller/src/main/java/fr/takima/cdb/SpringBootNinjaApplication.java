package fr.takima.cdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SpringBootNinjaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootNinjaApplication.class, args);
	}

	//TODO : add @Transactional
	//TODO : add DataSource
	//TODO : add @Service devant les services

}
