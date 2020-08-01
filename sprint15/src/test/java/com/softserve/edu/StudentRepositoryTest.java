package com.softserve.edu;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.softserve.edu.model.User;
import com.softserve.edu.model.User.Role;
import com.softserve.edu.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void addUserTest() {
		User expected = new User();
		expected.setRole(Role.TRAINEE);
		expected.setEmail("ben@mail.com");
		expected.setFirstName("Ben");
		expected.setLastName("Affleck");
		expected.setPassword("1234");
		
		userRepository.save(expected);
		User actual = userRepository.findByEmail("ben@mail.com");
		
		Assertions.assertEquals(expected, actual);
	}
	
	private List<User> addUsersToDB() {
		User expected1 = new User();
		expected1.setRole(Role.TRAINEE);
		expected1.setEmail("ivan@mail.com");
		expected1.setFirstName("Ivan");
		expected1.setLastName("Ivanov");
		expected1.setPassword("1234");
		userRepository.save(expected1);
		
		User expected2 = new User();
		expected2.setRole(Role.TRAINEE);
		expected2.setEmail("petr@mail.com");
		expected2.setFirstName("Petr");
		expected2.setLastName("Petrov");
		expected2.setPassword("1234");
		userRepository.save(expected2);
		
		User expected3 = new User();
		expected3.setRole(Role.MENTOR);
		expected3.setEmail("sem@mail.com");
		expected3.setFirstName("Sam");
		expected3.setLastName("Winchester");
		expected3.setPassword("1234");
		userRepository.save(expected3);
		
		User expected4 = new User();
		expected4.setRole(Role.MENTOR);
		expected4.setEmail("Dean@mail.com");
		expected4.setFirstName("Dean");
		expected4.setLastName("Winchester");
		expected4.setPassword("1234");
		userRepository.save(expected4);
		
		return new ArrayList<User>() {{
			add(expected1);
			add(expected2);
			add(expected3);
			add(expected4);
		}};
	}
	
	@Test
    public void findAllTest() {
		List<User> expected = addUsersToDB();
        
        List<User> actual = userRepository.findAll();

        Assertions.assertEquals(expected, actual);
    }
	
	@Test
    public void getAllByRoleTraineeTest(){
        List<User> expected = addUsersToDB().subList(0,2);
        
        List<User> actual = userRepository.getAllByRole(Role.TRAINEE);

        Assertions.assertEquals(expected, actual);
    }

	@Test
    public void getAllByRoleMentorTest(){
        List<User> expected = addUsersToDB().subList(2,4);
        
        List<User> actual = userRepository.getAllByRole(Role.MENTOR);

        Assertions.assertEquals(expected, actual);
    }
}
