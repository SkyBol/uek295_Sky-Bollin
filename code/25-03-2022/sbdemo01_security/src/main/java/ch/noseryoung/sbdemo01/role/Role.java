package ch.noseryoung.sbdemo01.role;


import ch.noseryoung.sbdemo01.authority.Authority;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;

    public Role(String name) {this.name = name;}


    public String toString() {return getName();}
}