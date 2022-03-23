package ch.noseryoung.sbdemo01.user;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value="Returns all Users")
    @GetMapping("/")
    public String getAllUser() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        List<UserEntity> users = userService.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if (!(i == users.size() - 2)) {stringBuilder.append(", ");}
            if (users.get(i) != null) {stringBuilder.append(users.get(i).toString());}
        }
        stringBuilder.append("]");
        return String.valueOf(stringBuilder);
    }
    @ApiOperation(value="Returns the User with the given ID")
    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int userId) {
        UserEntity user = userService.getUser(userId);
        if (user != null) {return user.toString();}
        return null;
    }

    @ApiOperation(value="Creates a User with the given ID, username and password")
    @PostMapping("/{id}")
    public String postOrder(@PathVariable("id") int userId, @RequestParam("username") String username, @RequestParam("password") String password) {
        if (!userService.postUser(userId, username, password)) {return "error";}
        return "done";
    }

    @ApiOperation(value="Updates the Users username and password with the given ID")
    @PutMapping("/{id}")
    public String putOrder(@PathVariable("id") int userId, @RequestParam("username") String username, @RequestParam("password") String password) {
        if (!userService.putUser(userId, username, password)) {return "error";}
        return "done";
    }

    @ApiOperation(value="Deletes the User with the given ID")
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id") int userId) {
        if (!userService.deleteUser(userId)) {return "error";}
        return "done";
    }
}
