package com.monitoramento.api_monitoramento.entity;

import jakarta.persistence.*;

@Entity
@Table(name="_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="username",length = 64, unique = true, nullable = false)
    private String username;

    @Column(name="email",unique = true,nullable = false)
    private String email;

    @Column(name="password_hash", length = 255, nullable = false)
    private String hash_password;

    @ManyToOne
    @JoinColumn(name="user_type_id")
    private UserType userType;

    public User(Long id, String username, String email, String hash_password, UserType userType) {
        Id = id;
        this.username = username;
        this.email = email;
        this.hash_password = hash_password;
        this.userType = userType;
    }

    //Analise and verify the UserType if the same is administrator
    public boolean isAdmin(){
        return UserType.Enum.ADMIN.get().equals(this.userType);
    }

}
