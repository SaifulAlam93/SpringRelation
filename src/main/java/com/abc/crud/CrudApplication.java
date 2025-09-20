package com.abc.crud;

import com.abc.crud.services.MyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}
//    @Bean
//    public CommandLineRunner run(MyService myService) {
//        return args -> {
//            myService.sayHello();
//            myService.doWork();
//        };
//    }
}
