package ch.noseryoung.sbdemo01.user;

import ch.noseryoung.sbdemo01.exception.ExceptionResponder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public ResponseEntity<List<User>> getAllUsers() {
            log.info("Returned all Users");
            return ResponseEntity.ok(userRepo.findAll());
    }
    public ResponseEntity<User> getUser(int userId) {
        try {
            User foundUser = userRepo.getById((long) userId);
            log.info("Returned found User");
            return ResponseEntity.ok(foundUser);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }
    public ResponseEntity<User> postUser(User newUser) {
        try {
            userRepo.save(newUser);
            log.info("User created");
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {return ResponseEntity.status(400).body(null);}
    }
    public ResponseEntity<User> putUser(User updatableUser) {
        if (getUser((int) updatableUser.getId()) == null) {return ResponseEntity.status(400).body(null);}
        userRepo.save(updatableUser);
        log.info("Updated User");
        return ResponseEntity.ok(updatableUser);
    }
    public ResponseEntity<String> deleteUser(int userId) {
        try {
            userRepo.deleteById((long) userId);
            return ResponseEntity.ok("done");
        } catch (Exception e) {return ResponseEntity.status(400).body("error");}
    }
}
