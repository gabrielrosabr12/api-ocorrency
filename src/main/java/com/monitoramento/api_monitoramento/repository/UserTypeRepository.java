package com.monitoramento.api_monitoramento.repository;

import com.monitoramento.api_monitoramento.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType,Long> {
}
