package com.softserve.edu;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.softserve.edu.model.Sprint;
import com.softserve.edu.repository.SprintRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SprintRepositoryTest {
	
	@Autowired
	private SprintRepository sprintRepository;
	
	@Test
    public void addSprintTest(){
        Sprint expected = new Sprint();
        expected.setStartDate(LocalDate.now());
        expected.setEndDate(LocalDate.now().plusDays(3));
        expected.setTitle("Sprint01");
        sprintRepository.save(expected);
        
        Sprint actual = sprintRepository.findById(1L).orElse(null);

        Assertions.assertEquals(expected, actual);
    }
	
	//TODO
}
