package ch.noseryoung.sbdemo01;

import ch.noseryoung.sbdemo01.authority.Authority;
import ch.noseryoung.sbdemo01.authority.AuthorityRepo;
import ch.noseryoung.sbdemo01.role.Role;
import ch.noseryoung.sbdemo01.role.RoleRepo;
import ch.noseryoung.sbdemo01.user.User;
import ch.noseryoung.sbdemo01.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class Sbdemo01Application {

	public static void main(String[] args) {
		SpringApplication.run(Sbdemo01Application.class, args);
	}

	@Autowired
	AuthorityRepo authRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	RoleRepo roleRepo;

	@EventListener
	public void prepareData(ApplicationReadyEvent ignore) {
		Authority create = new Authority("POST");
		Authority update = new Authority("UPDATE");
		Authority delete = new Authority("DELETE");
		Authority access = new Authority("ACCESS");

		authRepo.save(access);
		authRepo.save(create);
		authRepo.save(update);
		authRepo.save(delete);

		Role admin = new Role("ADMIN", List.of(access, create, update, delete));
		Role staff = new Role("STAFF", List.of(access, update));
		Role viewer = new Role("VIEWER", List.of(access));

		roleRepo.save(admin);
		roleRepo.save(staff);
		roleRepo.save(viewer);

		User adminUser = new User("Admin", "132", admin);
		userRepo.save(adminUser);
	}

	@Bean
	PasswordEncoder passwordEncoder() {return NoOpPasswordEncoder.getInstance();}
}
