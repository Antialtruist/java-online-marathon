package com.softserve.edu.service;

import com.softserve.edu.dto.UserRequest;
import com.softserve.edu.dto.UserResponce;
import com.softserve.edu.model.Marathon;
import com.softserve.edu.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getUserById(Long id);
    UserDetails loadUserByUsername(String username);
    User createOrUpdateUser( User user);
    void deleteUserById(Long id);
    boolean addUserToMarathon(User user, Marathon marathon);
    boolean deleteUserFromMarathon(User user, Marathon marathon);
    User getUserByEmail(String email);
    UserResponce findByLoginAndPassword(UserRequest userRequest);

    boolean saveUser(UserRequest userRequest);
}
