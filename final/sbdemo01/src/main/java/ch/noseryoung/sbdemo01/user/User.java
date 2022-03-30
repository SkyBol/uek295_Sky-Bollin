package ch.noseryoung.sbdemo01.user;

import ch.noseryoung.sbdemo01.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank(message = "cannot be empty")
    @NotNull
    private String username;

    @NotBlank(message = "cannot be empty")
    @NotNull
    private String password;

    @ManyToOne
    @JoinColumn(name="role_id", referencedColumnName="id")
    private Role role;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void setRole(Role role) {this.role = role;}

    public long getId() {return userId;}
    public Role getRole() {return role;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public void setUserId(int id) {this.userId = (long)id;}
}
