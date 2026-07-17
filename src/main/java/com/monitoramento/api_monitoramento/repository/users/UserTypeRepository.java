package com.monitoramento.api_monitoramento.repository.users;

import com.monitoramento.api_monitoramento.entity.users.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType,Long> {
}
