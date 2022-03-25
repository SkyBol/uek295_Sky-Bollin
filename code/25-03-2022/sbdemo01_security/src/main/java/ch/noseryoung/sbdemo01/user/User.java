package ch.noseryoung.sbdemo01.user;

import ch.noseryoung.sbdemo01.role.Role;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;

    @NotBlank(message = "cannot be empty")
    @Column(name="username")
    private String username;

    @NotBlank(message = "cannot be empty")
    @Column(name="password")
    private String password;

    @ManyToOne
    @JoinColumn(name="role_id", referencedColumnName="id")
    private Role role;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(int userId, String username, String password) {
        this.user_id = (long) userId;
        this.username = username;
        this.password = password;
    }
    public User(int userId, String username, String password, Role role) {
        this.user_id = (long) userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public User(Long userId, String username, String password, Role role) {
        this.user_id = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setRole(Role role) {this.role = role;}

    public long getId() {return user_id;}
    public Role getRole() {return role;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}

    public UserDTO getDTO() {return new UserDTO(user_id, username, password, role);}
}
