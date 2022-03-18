package ch.noseryoung.sbdemo01.user;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Table(name="user")
public class UserEntity {
    @Id
    @Column(name="user_id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    public UserEntity(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "username=" + username +
                ", password=" + password +
                '}';
    }
}
