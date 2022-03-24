package ch.noseryoung.sbdemo01.user;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/user")
@EnableSwagger2
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value="Returns all Users")
    @GetMapping("/")
    public ResponseEntity<String> getAllUser() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        List<User> users = userService.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if (i != users.size() - 2) {stringBuilder.append(", ");}
            if (users.get(i) != null) {stringBuilder.append(users.get(i).toString());}
        }
        stringBuilder.append("]");
        return ResponseEntity.ok(stringBuilder.toString());
    }
    @ApiOperation(value="Returns the User with the given ID")
    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(@PathVariable("id") int userId) {
        User user = userService.getUser(userId);
        if (user != null) {return ResponseEntity.ok(user.toString());}
        return ResponseEntity.status(404).body("error");
    }
    @ApiOperation(value="Creates a User with the given ID, username and password")
    @PostMapping("/")
    public ResponseEntity<User> postUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        User createdUser = userService.postUser(username, password);
        if (createdUser == null) {return ResponseEntity.status(400).body(null);}
        return ResponseEntity.ok(createdUser);
    }
    @ApiOperation(value="Updates the Users username and password with the given ID")
    @PutMapping("/{id}")
    public ResponseEntity<User> putUser(@PathVariable("id") int userId, @RequestParam("username") String username, @RequestParam("password") String password) {
        User updatedUser = userService.putUser(userId, username, password);
        if (updatedUser == null) {return ResponseEntity.status(400).body(null);}
        return ResponseEntity.ok(updatedUser);
    }
    @ApiOperation(value="Deletes the User with the given ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int userId) {
        if (!userService.deleteUser(userId)) {return ResponseEntity.status(400).body("error");}
        return ResponseEntity.ok("done");
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundException(Exception e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
