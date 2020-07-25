package com.telukhin.service;

import com.telukhin.domain.Marathon;

import com.telukhin.domain.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getUserById(long id);
    User createOrUpdateUser(User user);
    List<User> getAllByRole(String role);
    boolean addUserToMarathon(User user, Marathon marathon);

}
