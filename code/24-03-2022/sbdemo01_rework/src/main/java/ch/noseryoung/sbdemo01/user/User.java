package ch.noseryoung.sbdemo01.user;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @NotBlank(message = "cannot be empty")
    @Column(name="username")
    private String username;

    @NotBlank(message = "cannot be empty")
    @Column(name="password")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User(int userId, String username, String password) {
        this.user_id = (long) userId;
        this.username = username;
        this.password = password;
    }

    public long getId() {return user_id;}

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
