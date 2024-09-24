package com.Service.UserService;

import com.Service.UserService.Enum.UserStatus;
import com.Service.UserService.Enum.UserType;
import com.Service.UserService.Repository.UserRepository;
import com.Service.UserService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	public static void main(String[] args){
		SpringApplication.run(UserServiceApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		User trasanctionService=User.builder()
				.phoneNo("transaction-service")
				.password(passwordEncoder.encode("transaction-service"))
				.userStatus(UserStatus.ACTIVE)
				.authorities("SERVICE")
		        .userType(UserType.ADMIN)
				.email("transaction@gmail.com").build();
		if(userRepository.findByPhoneNo("transaction-service")==null)
			userRepository.save(trasanctionService);
	}
}
