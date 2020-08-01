package com.softserve.edu;

import com.softserve.edu.exception.EntityNotFoundException;
import com.softserve.edu.model.Marathon;
import com.softserve.edu.model.User;
import com.softserve.edu.repository.UserRepository;
import com.softserve.edu.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;


    private List<User> users;

    @BeforeEach
    public void setUp() {

        User user = new User();
        user.setId(1L);
        user.setRole(User.Role.TRAINEE);
        user.setPassword("pi");
        user.setEmail("pi@pi.ru");
        user.setFirstName("first");
        user.setLastName("g1");

        User user2 = new User();
        user2.setId(2L);
        user2.setRole(User.Role.MENTOR);
        user2.setPassword("44");
        user2.setEmail("ma@nt.or");
        user2.setFirstName("Men");
        user2.setLastName("Tor");

        //userRepository.save(user);

        List<User> menthors = new ArrayList<>();
        menthors.add(user2);

        Mockito.when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        users = new ArrayList<>();
        users.add(user);
        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(userRepository.getAllByRole(User.Role.MENTOR)).thenReturn(menthors);
    }

    @Test
    public void whenValidId_thenStudentShouldBeFound() {

        Long id = 1L;
        User found = userRepository.findById(id).orElse(new User());
        Assertions.assertEquals(id, found.getId());
        //assertThat(found.getId()).isEqualTo(id);
    }




    @Test
    public void findAllStudentsTest() {

        // I cleared copy of list to fail test. if pass original collection than test will be passed
        List<User> usersTest = userRepository.findAll();
       // ArrayList<User> copy = new ArrayList<>(usersTest);
        //copy.clear();

        Assertions.assertEquals(users, usersTest);
        //assertThat(found.getId()).isEqualTo(id);
    }

    @Test
    public void findAllStudentsByRoleTest() {

        Long id = 2L;
        List<User> usersTest = userRepository.getAllByRole(User.Role.MENTOR);
        User found = usersTest.get(0);
        Assertions.assertEquals(id, found.getId());

    }


    /*
    public User createOrUpdateUser(User entity) {
        return userRepository.save(entity);
    }
    public List<User> getAllByRole(String role) {
        return userRepository.getAllByRole(User.Role.valueOf(role.toUpperCase()));
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
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmailIs(email);
    }*/
    @Test
    public void deleteUserByIdTest() {

        Long id = 1L;
        userRepository.deleteById(id);

        verify(userRepository, times(1)).deleteById(eq(1L));
    }

}
