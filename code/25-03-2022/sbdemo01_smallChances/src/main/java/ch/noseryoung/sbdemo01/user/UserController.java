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
    public ResponseEntity<List<UserDTO>> getAllUser() {return userService.getAllUsers();}
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") int userId) {return userService.getUser(userId);}
    @PostMapping("/")
    public ResponseEntity<UserDTO> postUser(@RequestBody UserDTO newUser) {return userService.postUser(newUser.getUser());}
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> putUser(@RequestBody UserDTO updatableUser) {return userService.putUser(updatableUser.getUser());}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int userId) {return userService.deleteUser(userId);}
}
