package com.monitoramento.api_monitoramento.entity.users;

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

    @Column(name="registration", length = 6,nullable = false,unique = true)
    private String registration;

    @Column(name="is_enabled")
    private boolean isEnabled;

    public User() {
    }

    @ManyToOne
    @JoinColumn(name="user_type")
    private UserType userType;

    public User(String username, String email, String hash_password, UserType userType,String registration) {
        this.username = username;
        this.email = email;
        this.hash_password = hash_password;
        this.userType = userType;
        this.registration = registration;
    }

    //Analise and verify the UserType if the same is administrator
    public boolean isAdmin(){
        return UserType.Enum.ADMIN.get().equals(this.userType);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userType.equals(UserType.Enum.ADMIN.get())) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_COLLABORATOR"),
                    new SimpleGrantedAuthority("ROLE_USER"));
        } else if (this.userType.equals(UserType.Enum.COLLABORATOR)) {
            return List.of(new SimpleGrantedAuthority("ROLE_COLLABORATOR"),
                    new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));

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
        return this.isEnabled;
    }

    @Override
    public void eraseCredentials() {
        this.hash_password = null;
    }
}
