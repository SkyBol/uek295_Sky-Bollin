package ch.noseryoung.sbdemo01.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUser() {return userService.getAllUsers();}
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int userId) {return userService.getUser(userId);}
    @PostMapping("/")
    public ResponseEntity<User> postUser(@RequestBody @Valid User newUser) {return userService.postUser(newUser);}
    @PutMapping("/{id}")
    public ResponseEntity<User> putUser(@RequestBody @Valid User updatableUser) {return userService.putUser(updatableUser);}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int userId) {return userService.deleteUser(userId);}

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> exceptionHandler(RuntimeException re) {
        log.warn("RuntimeException:" + re.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(re.getMessage());
    }
}
