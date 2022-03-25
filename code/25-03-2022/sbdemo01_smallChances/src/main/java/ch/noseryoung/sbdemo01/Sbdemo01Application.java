package ch.noseryoung.sbdemo01;

import ch.noseryoung.sbdemo01.role.Role;
import ch.noseryoung.sbdemo01.role.RoleRepo;
import ch.noseryoung.sbdemo01.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Sbdemo01Application {

	public static void main(String[] args) {
		SpringApplication.run(Sbdemo01Application.class, args);
	}

	@Autowired
	UserRepo userRepo;
	@Autowired
	RoleRepo roleRepo;

	public void run(String... args) throws Exception {
		roleRepo.save(new Role("DEFAULT"));
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	// NoOpPasswordEncoder.getInstance()
}
