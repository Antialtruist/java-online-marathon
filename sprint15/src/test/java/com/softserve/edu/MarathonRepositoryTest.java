package com.softserve.edu;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.softserve.edu.model.Marathon;
import com.softserve.edu.repository.MarathonRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MarathonRepositoryTest {
	
	@Autowired
    private MarathonRepository marathonRepository;
	
	@Test
	public void addMarathonTest() {
		Marathon expected = new Marathon();
		expected.setTitle("JOM");
		marathonRepository.save(expected);
		
		Marathon actual = marathonRepository.findById(5L).orElse(null);
		
		Assertions.assertEquals(expected, actual);
	}
	
	private List<Marathon> addMarathonsToDB() {
        Marathon marathon1 = new Marathon();
        marathon1.setTitle("JavaOnlineMarathone");
        marathonRepository.save(marathon1);

        Marathon marathon2 = new Marathon();
        marathon2.setTitle("JavaScriptOnlineMarathone");
        marathonRepository.save(marathon2);

        return new ArrayList<Marathon>() {{
            add(marathon1);
            add(marathon2);
        }};
    }
	
	@Test
    public void findAllMarathonsTest() {
        List<Marathon> expected = addMarathonsToDB();
        
        List<Marathon> actual = marathonRepository.findAll();

        Assertions.assertEquals(expected, actual);
    }
	
	@Test
    public void findByIdTest() {
        Marathon expected = addMarathonsToDB().get(0);
        
        Marathon actual = marathonRepository.findById(3L).orElse(null);

        Assertions.assertEquals(expected, actual);
    }
	
//	@Test
//    public void deleteMarathonTest() {
//        List<Marathon> marathonsList = addMarathonsToDB();
//        marathonRepository.delete(marathonRepository.findById(2L).get());
//
//        List<Marathon> expected = marathonsList.subList(0, 2);
//        
//        List<Marathon> actual = marathonRepository.findAll();
//
//        Assertions.assertEquals(expected, actual);
//    }
}
