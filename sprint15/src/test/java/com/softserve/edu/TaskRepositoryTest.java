package com.softserve.edu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.softserve.edu.model.Task;
import com.softserve.edu.repository.TaskRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskRepositoryTest {
	
	@Autowired
    private TaskRepository taskRepository;
	
	@Test
    public void addTaskTest() {
        Task expected = new Task();
        expected.setTitle("Task1");
        expected.setUpdated(LocalDate.now());
        expected.setCreated(LocalDate.now());
        taskRepository.save(expected);
        
        Task actual = taskRepository.findById(1L).orElse(null);

        Assertions.assertEquals(expected, actual);
    }
	
	private List<Task> addTasksToDB() {
        Task task1 = new Task();
        task1.setTitle("FirstTask");
        task1.setUpdated(LocalDate.now());
        task1.setCreated(LocalDate.now());
        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setTitle("SecondTask");
        task2.setUpdated(LocalDate.now());
        task2.setCreated(LocalDate.now());
        taskRepository.save(task2);

        return new ArrayList<Task>() {{
            add(task1);
            add(task2);
        }};
    }
	
	@Test
    public void findByIdTest() {
        Task expected = addTasksToDB().get(0);
        
        Task actual = taskRepository.findById(1L).orElse(null);

        Assertions.assertEquals(expected, actual);
    }
	
	@Test
    public void findAllTasksTest() {
        List<Task> expected = addTasksToDB();
        
        List<Task> actual = taskRepository.findAll();

        Assertions.assertEquals(expected, actual);
    }
	
	@Test
    public void deleteTaskTest() {
        List<Task> all = addTasksToDB();
        taskRepository.delete(taskRepository.findById(2L).get());

        List<Task> expected = all.subList(0, 2);
        
        List<Task> actual = taskRepository.findAll();

        Assertions.assertEquals(expected, actual);
    }
}