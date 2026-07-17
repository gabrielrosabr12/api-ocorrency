package com.monitoramento.api_monitoramento.repository.users;

import com.monitoramento.api_monitoramento.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByUsername(String username);

    public User findByUsernameOrEmail(String username,String email);

}
