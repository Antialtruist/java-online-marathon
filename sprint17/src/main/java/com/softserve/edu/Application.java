package com.softserve.edu;

import com.softserve.edu.model.User;
import com.softserve.edu.repository.UserRepository;
import com.softserve.edu.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Application implements CommandLineRunner {

//	private final UserService userService;
//	private final MarathonService marathonService;
//	private final SprintService sprintService;
//	private final TaskService taskService;
//	private final ProgressService progressService;
//
//	public Application(UserService userService, MarathonService marathonService, SprintService sprintService, TaskService taskService, ProgressService progressService) {
//		this.userService = userService;
//		this.marathonService = marathonService;
//		this.sprintService = sprintService;
//		this.taskService = taskService;
//		this.progressService = progressService;
//	}
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {	
	}
}