package ch.noseryoung.sbdemo01.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ControllerUser {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getAllUser() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        List<UserEntity> users = userService.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if (!(i == users.size() - 2)) {stringBuilder.append(", ");}
            stringBuilder.append(users.get(i).toString());
        }
        stringBuilder.append("]");
        return String.valueOf(stringBuilder);
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int userId) {
        return userService.getUser(userId).toString();
    }

    @PostMapping("/{id}")
    public String postOrder(
            @PathVariable("id") int userId,
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        if (!userService.postUser(userId, username, password)) {return "error";}
        return "done";
    }

    @PutMapping("/{id}")
    public String putOrder(
            @PathVariable("id") int userId,
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        if (!userService.putUser(userId, username, password)) {return "error";}
        return "done";
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id") int userId) {
        if (!userService.deleteUser(userId)) {return "error";}
        return "done";
    }
}
