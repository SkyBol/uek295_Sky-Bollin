package ch.noseryoung.sbdemo01.authority;


import lombok.*;

import javax.persistence.*;

@Data@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}