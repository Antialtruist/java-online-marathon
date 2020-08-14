package com.softserve.edu.service.impl;

import com.softserve.edu.dto.UserRequest;
import com.softserve.edu.dto.UserResponce;
import com.softserve.edu.exception.EntityNotFoundException;
import com.softserve.edu.model.Marathon;
import com.softserve.edu.model.Role;
import com.softserve.edu.model.User;
import com.softserve.edu.repository.MarathonRepository;
import com.softserve.edu.repository.RoleRepository;
import com.softserve.edu.repository.UserRepository;
import com.softserve.edu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final MarathonRepository marathonRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           MarathonRepository marathonRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.marathonRepository = marathonRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        initDefaultData();
    }

    private void initDefaultData() {
        if ((roleRepository.count() == 0)
                && (userRepository.count() == 0)) {
            Role roleMentor = new Role();
            roleMentor.setName("ROLE_MENTOR");
            Role roleStudent = new Role();
            roleStudent.setName("ROLE_TRAINEE");
            roleRepository.save(roleMentor);
            roleRepository.save(roleStudent);

            User mentor = new User();
            mentor.setFirstName("Mentor");
            mentor.setLastName("Mentor");
            mentor.setEmail("mentor@g.com");
            mentor.setRole(roleMentor);
            mentor.setPassword(passwordEncoder.encode("123"));
            userRepository.save(mentor);
        }
    }

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("No user /w id "+id)));
    }

    public User createOrUpdateUser(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public boolean addUserToMarathon(User user, Marathon marathon) {
        User userEntity = userRepository.getOne(user.getId());
        Marathon marathonEntity = marathonRepository.getOne(marathon.getId());
        marathonEntity.getUsers().add(userEntity);
        return marathonRepository.save(marathonEntity) != null;
    }

    @Override
    public boolean deleteUserFromMarathon(User user, Marathon marathon) {
        User userEntity = userRepository.getOne(user.getId());
        Marathon marathonEntity = marathonRepository.getOne(marathon.getId());
        marathonEntity.getUsers().remove(userEntity);
        return marathonRepository.save(marathonEntity) != null;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public UserResponce findByLoginAndPassword(UserRequest userRequest) {
        UserResponce result = null;
        User user = userRepository.getUserByEmail(userRequest.getLogin());
        if ((user != null) && (passwordEncoder.matches(userRequest.getPassword(), user.getPassword()))) {
            result = new UserResponce();
            result.setLogin(userRequest.getLogin());
            result.setRolename(user.getRole().getName());
        }
        return result;
    }

    @Override
    public boolean saveUser(UserRequest userRequest) {
        User user = new User();
        user.setRole(roleRepository.findRoleByName("ROLE_TRAINEE"));
        user.setEmail(userRequest.getLogin());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        return userRepository.save(user) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not Found!");
        }
        return user;
    }
}
