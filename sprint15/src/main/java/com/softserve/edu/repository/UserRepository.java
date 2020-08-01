package com.softserve.edu.repository;


import com.softserve.edu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.validation.constraints.Email;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> getAllByRole(User.Role role);
    User findByEmail(@Email String email);
}
