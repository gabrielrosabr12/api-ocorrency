package com.monitoramento.api_monitoramento.repository;

import com.monitoramento.api_monitoramento.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByUsername(String username);

}
