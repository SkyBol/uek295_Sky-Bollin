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

    @GetMapping("/{username}")
    public String getUser(@PathVariable("username") String username) {
        return userService.getUser(username).toString();
    }
    //TODO: Make Put not create
    @PutMapping("/{username}")
    @PostMapping("/{username}")
    public String postAndPutOrder(
            @PathVariable("username") String username,
            @RequestParam("password") String password,
            @RequestParam("id") String id) {
        if (!userService.postUser(id, username, password)) {return "error";}
        return "done";
    }
    @DeleteMapping("/{username}")
    public String deleteOrder(@PathVariable("username") String username) {
        if (!userService.deleteUser(username)) {return "error";}
        return "done";
    }
}
