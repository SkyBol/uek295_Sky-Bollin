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
    @Column(name = "user_id")
    private Long userId;

    @NotBlank(message = "cannot be empty")
    private String username;

    @NotBlank(message = "cannot be empty")
    private String password;

    @ManyToOne
    @JoinColumn(name="role_id", referencedColumnName="id")
    private Role role;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setRole(Role role) {this.role = role;}

    public long getId() {return userId;}
    public Role getRole() {return role;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
}
