package com.telukhin.service.Impl;

import com.telukhin.domain.Marathon;
import com.telukhin.domain.User;
import com.telukhin.repos.MarathonRepo;
import com.telukhin.repos.UserRepo;
import com.telukhin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    final private UserRepo userRepo;
    @Autowired
    final private MarathonRepo marathonRepo;


    public UserServiceImpl(UserRepo userRepo, MarathonRepo marathonRepo) {
        this.userRepo = userRepo;

        this.marathonRepo = marathonRepo;
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(long id) {
        Optional<User> user = userRepo.findById(id);
        return user.orElse(null);
    }

    @Override
    public User createOrUpdateUser(User user) {
        if (user.getId() != null) {
            Optional<User> entity = userRepo.findById(user.getId());
            if (entity.isPresent()) {
                User newUser = entity.get();
                newUser.setEmail(user.getEmail());
                newUser.setFirstName(user.getFirstName());
                newUser.setRole(user.getRole());
                newUser.setPassword(user.getPassword());
                newUser = userRepo.save(newUser);
                return newUser;
            }
        }
        user = userRepo.save(user);
        return user;
    }

    @Override
    public List<User> getAllByRole(String role) {
        return userRepo.getAllByRole(User.Role.valueOf(role));
    }

    @Override
    public boolean addUserToMarathon(User user, Marathon marathon) {
        Optional<Marathon> marathonFromBd = marathonRepo.findById(marathon.getId());
        if (marathonFromBd.isPresent()) {
            marathonFromBd.get().getUsers().add(user);
            marathonRepo.save(marathonFromBd.get());
            return true;
        }
        return false;
    }
}
