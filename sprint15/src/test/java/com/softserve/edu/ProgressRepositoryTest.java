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

import com.softserve.edu.model.Progress;
import com.softserve.edu.model.Task;
import com.softserve.edu.model.User;
import com.softserve.edu.model.User.Role;
import com.softserve.edu.repository.ProgressRepository;
import com.softserve.edu.repository.TaskRepository;
import com.softserve.edu.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProgressRepositoryTest {
	
	private UserRepository userRepository;
    private TaskRepository taskRepository;
    private ProgressRepository progressRepository;
    
    private List<Task> tasks = new ArrayList<>();
    private User student = new User();
    
    @Autowired
	public ProgressRepositoryTest(UserRepository userRepository, TaskRepository taskRepository,
			ProgressRepository progressRepository) {
		this.userRepository = userRepository;
		this.taskRepository = taskRepository;
		this.progressRepository = progressRepository;
	}
	
	private List<Progress> addProgressesToDB() {
        student.setRole(Role.TRAINEE);
        student.setEmail("marsh@mail.com");
        student.setFirstName("Marshal");
        student.setLastName("Mathers");
        student.setPassword("1234");
        this.student = userRepository.save(student);

        Task task1 = new Task();
        task1.setTitle("Task1");
        task1.setUpdated(LocalDate.now());
        task1.setCreated(LocalDate.now());
        tasks.add(taskRepository.save(task1));

        Task task2 = new Task();
        task2.setTitle("Task2");
        task2.setUpdated(LocalDate.now());
        task2.setCreated(LocalDate.now());
        tasks.add(taskRepository.save(task2));

        Progress progress1 = new Progress();
        progress1.setTask(task1);
        progress1.setTrainee(student);
        progress1.setStarted(LocalDate.now());
        progress1.setStarted(LocalDate.now().plusDays(3));
        progress1.setStatus(Progress.TaskStatus.PASS);
        progressRepository.save(progress1);

        Progress progress2 = new Progress();
        progress2.setTask(task2);
        progress1.setTrainee(student);
        progress2.setStarted(LocalDate.now());
        progress2.setStarted(LocalDate.now().plusDays(3));
        progress2.setStatus(Progress.TaskStatus.PENDING);
        progressRepository.save(progress2);

        return new ArrayList<Progress>() {{
            add(progress1);
            add(progress2);
        }};
    }
    
	@Test
    public void addProgressTest(){
        Progress expected = this.addProgressesToDB().get(0);
        
        Progress actual = progressRepository.findById(1L).orElse(null);

        Assertions.assertEquals(expected, actual);
    }

	//TODO findAll & findById
}
