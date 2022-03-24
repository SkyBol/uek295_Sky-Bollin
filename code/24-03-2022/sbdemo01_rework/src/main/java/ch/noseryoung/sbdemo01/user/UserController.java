package ch.noseryoung.sbdemo01.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<User> postUser(@RequestBody User newUser) {return userService.postUser(newUser);}
    @PutMapping("/{id}")
    public ResponseEntity<User> putUser(@RequestBody User updatableUser) {return userService.putUser(updatableUser);}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int userId) {return userService.deleteUser(userId);}
}
