package com.telukhin.repos;

import com.telukhin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    List<User> getAllByRole(User.Role Role);
}
