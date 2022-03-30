package ch.noseryoung.sbdemo01.user;

import ch.noseryoung.sbdemo01.role.Role;
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

    public ResponseEntity<List<User>> getAllUsers() {
            log.info("Returned all Users");
            List<User> users = userRepo.findAll();
            return ResponseEntity.ok(users);
    }
    public ResponseEntity<User> getUser(int userId) {
        try {
            User foundUser = userRepo.getById((long) userId);
            log.info("Returned found User");
            return ResponseEntity.ok(foundUser);
        } catch (Exception e) {return ResponseEntity.status(404).body(null);}
    }
    public ResponseEntity<User> postUser(User newUser) {
        try {
            if (newUser.getRole() == null) {newUser.setRole(roleRepo.getByName("VIEWER"));}
            userRepo.save(newUser);
            log.info("User created");
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {return ResponseEntity.status(400).body(null);}
    }
    public ResponseEntity<User> putUser(User updatableUser) {
        try {
            if (getUser((int) updatableUser.getId()) == null) {return ResponseEntity.status(400).body(null);}
            userRepo.save(updatableUser);
            log.info("Updated User");
            return ResponseEntity.ok(updatableUser);
        } catch (Exception e) {return ResponseEntity.status(400).body(null);}
    }
    public ResponseEntity<String> deleteUser(int userId) {
        try {
            userRepo.deleteById((long) userId);
            return ResponseEntity.ok("done");
        } catch (Exception e) {return ResponseEntity.status(400).body("error");}
    }

    public Role getRoleByID(int id) {return roleRepo.getById((long)id);}

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
}
