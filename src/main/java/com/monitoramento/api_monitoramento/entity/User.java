package com.monitoramento.api_monitoramento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="_user")
public class User implements UserDetails, CredentialsContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="username",length = 64, unique = true, nullable = false)
    private String username;

    @Column(name="email",unique = true,nullable = false)
    private String email;

    @Column(name="password_hash", length = 255, nullable = false)
    private String hash_password;

    public User() {
    }

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        switch (this.getUserType().getId()){
            case 3L:
                return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_MONITO"),
                        new SimpleGrantedAuthority("ROLE_USER"));
            default:


        }



    }

    @Override
    public @Nullable String getPassword() {
        return this.hash_password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public void eraseCredentials() {
        this.hash_password = null;
    }
}
