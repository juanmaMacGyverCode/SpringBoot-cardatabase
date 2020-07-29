package com.juanmanuel.cardatabase.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    public User(String username, String password, String role){
        super();
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
