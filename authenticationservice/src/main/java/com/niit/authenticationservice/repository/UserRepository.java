package com.niit.authenticationservice.repository;

import com.niit.authenticationservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserEmailAndPassword(String userEmail,String password);
}
