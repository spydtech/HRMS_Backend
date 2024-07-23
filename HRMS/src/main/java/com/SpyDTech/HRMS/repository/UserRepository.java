package com.SpyDTech.HRMS.repository;


import com.SpyDTech.HRMS.entities.Role;
import com.SpyDTech.HRMS.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByEmail(String email);

    Optional<User> findByEmail(String email);


    boolean existsByEmail(String email);




}
