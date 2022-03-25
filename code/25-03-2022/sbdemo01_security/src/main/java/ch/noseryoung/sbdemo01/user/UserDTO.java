package ch.noseryoung.sbdemo01.user;

import ch.noseryoung.sbdemo01.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String username;
    private String password;
    private Role role;

    public User getUser() {return new User(userId, username, password, role);}
}
