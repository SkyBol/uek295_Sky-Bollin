package ch.noseryoung.sbdemo01.user;

import ch.noseryoung.sbdemo01.role.RoleRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Log4j2
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    public ResponseEntity<List<UserDTO>> getAllUsers() {
            log.info("Returned all Users");
            List<User> users = userRepo.findAll();
            ArrayList<UserDTO> userDTOs = new ArrayList<>();
            users.forEach(user -> userDTOs.add(user.getDTO()));
            return ResponseEntity.ok(userDTOs);
    }
    public ResponseEntity<UserDTO> getUser(int userId) {
        try {
            UserDTO foundUser = userRepo.getById((long) userId).getDTO();
            if (foundUser == null) {return ResponseEntity.status(400).body(null);}
            log.info("Returned found User");
            return ResponseEntity.ok(foundUser);
        } catch (Exception e) {return ResponseEntity.status(404).body(null);}
    }
    public ResponseEntity<UserDTO> postUser(User newUser) {
        try {
            if (newUser.getRole() == null || roleRepo.getById(newUser.getRole().getId()) == null) {newUser.setRole(roleRepo.getByName("DEFAULT"));}
            userRepo.save(newUser);
            log.info("User created");
            return ResponseEntity.ok(newUser.getDTO());
        } catch (Exception e) {return ResponseEntity.status(400).body(null);}
    }
    public ResponseEntity<UserDTO> putUser(User updatableUser) {
        try {
            if (getUser((int) updatableUser.getId()) == null) {return ResponseEntity.status(400).body(null);}
            userRepo.save(updatableUser);
            log.info("Updated User");
            return ResponseEntity.ok(updatableUser.getDTO());
        } catch (Exception e) {return ResponseEntity.status(400).body(null);}
    }
    public ResponseEntity<String> deleteUser(int userId) {
        try {
            userRepo.deleteById((long) userId);
            return ResponseEntity.ok("done");
        } catch (Exception e) {return ResponseEntity.status(400).body("error");}
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null){throw new UsernameNotFoundException("User not found");}
        else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRole().getAuthorities().forEach(authority ->
                    authorities.add(new SimpleGrantedAuthority(authority.getName())));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    @ExceptionHandler
    public ResponseEntity<String> userNotFoundException() {
        return ResponseEntity.status(404).body("user not found");
    }
}
