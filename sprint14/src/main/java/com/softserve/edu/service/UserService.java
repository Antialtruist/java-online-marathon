package com.softserve.edu.service;

import com.softserve.edu.model.Marathon;
import com.softserve.edu.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getUserById(Long id);
    User createOrUpdateUser(User user);
    List<User> getAllByRole(User.Role role);
    boolean addUserToMarathon(User user, Marathon marathon);
    List<User> getStudentsByMarathon(Long marathonId);
    void removeFromMarathon(Long studentId, Long marathonId);
    void deleteUserById(Long userId);
}
