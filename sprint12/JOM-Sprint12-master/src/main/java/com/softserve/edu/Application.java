package com.softserve.edu;

import com.softserve.edu.entity.Entity;
import com.softserve.edu.service.DataService;
import com.softserve.edu.service.MarathonService;
import com.softserve.edu.service.impl.DataServiceImpl;
import com.softserve.edu.service.impl.MarathonServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
