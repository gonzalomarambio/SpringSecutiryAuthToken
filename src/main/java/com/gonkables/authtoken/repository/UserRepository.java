package com.gonkables.authtoken.repository;

import com.gonkables.authtoken.entities.UserTo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<UserTo, Long> {

    UserTo findByUsermane(String username);

    UserTo findByToken(String token);

}