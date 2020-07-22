package com.softserve.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.softserve.edu.dto.AverageScore;
import com.softserve.edu.dto.MentorStudent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.softserve.edu.dto.SprintScore;
import com.softserve.edu.dto.StudentScore;
import com.softserve.edu.service.DataService;
import com.softserve.edu.service.MarathonService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class ApplicationTest {

    private MarathonService marathonService;

    private DataService dataService;

    @Autowired
    public ApplicationTest(MarathonService marathonService, DataService dataService) {
        this.marathonService = marathonService;
        this.dataService = dataService;
        fillDataService();
    }

    private void fillDataService() {
        //addStudent
        dataService.addStudent("Mykola");
        dataService.addStudent("Denys");
        dataService.addStudent("Petro");
        //addMentor
        dataService.addMentor("Mykola");
        dataService.addMentor("Yaroslav");
        dataService.addMentor("Nataliia");
        //addSprints
        dataService.addSprint("Sprint1");
        dataService.addSprint("Sprint2");
        //addCommunication
        dataService.addCommunication("Mykola", "Yaroslav");
        dataService.addCommunication("Denys","Yaroslav");
        dataService.addCommunication("Petro", "Nataliia");
        //addSolution
        dataService.addSolution("Mykola", "Sprint1", 100);
        dataService.addSolution("Mykola", "Sprint2", 50);
        dataService.addSolution("Denys", "Sprint1", 67);
        dataService.addSolution("Denys", "Sprint2", 90);
        dataService.addSolution("Petro","Sprint1", 75);
        dataService.addSolution("Petro", "Sprint2", 0);
    }

    @Test
    public void checkGetStudents() {
        List<String> expected = Arrays.asList("Mykola","Denys","Petro");
        List<String> actual = marathonService.getStudents();
        Assertions.assertEquals(expected, actual, "checkGetStudents()");
    }

    @Test
    public void checkGetMentors() {
        List<String> expected = Arrays.asList("Mykola","Yaroslav","Nataliia");
        List<String> actual = marathonService.getMentors();
        Assertions.assertEquals(expected, actual, "checkGetMentors()");
    }

    @Test
    public void checkStudentResult() {
    	StudentScore expected = new StudentScore("Denys", Arrays.asList(
                new SprintScore("Sprint1",67 ),
                new SprintScore("Sprint2",90 )
        ));
        StudentScore actual = marathonService.studentResult("Denys");
        Assertions.assertEquals(expected, actual, "checkStudentResult()");
    }

    @Test
    public void checkAllStudentsResult() {
    	List<StudentScore> expected = new ArrayList<>();
        expected.add(
                new StudentScore("Mykola", Arrays.asList(
                        new SprintScore("Sprint1",100 ),
                        new SprintScore("Sprint2",50 )
                )));
        expected.add(
                new StudentScore("Denys", Arrays.asList(
                        new SprintScore("Sprint1",67 ),
                        new SprintScore("Sprint2",90 )
                )));
        expected.add(
                new StudentScore("Petro", Arrays.asList(
                        new SprintScore("Sprint1",75 ),
                        new SprintScore("Sprint2",0 )
                )));
    	List<StudentScore> actual = marathonService.allStudentsResult();
    	Assertions.assertEquals(expected, actual, "checkAllStudentsResult()");
    }


    @Test
    public void checkStudentAverage() {
        marathonService.studentAverage("Mykola");
        AverageScore actual = new AverageScore("Mykola",75.0D);
        AverageScore expected = marathonService.studentAverage("Mykola");
        Assertions.assertEquals(expected, actual, "checkMentorStudents()");
    }

    @Test
    public void checkAllStudentsAverage() {
        List<AverageScore> expected = Arrays.asList(
                new AverageScore("Mykola", 75.0D),
                new AverageScore("Denys", 78.5D),
                new AverageScore("Petro", 37.5D)
                );
        List<AverageScore> actual = marathonService.allStudentsAverage();
        Assertions.assertEquals(expected, actual, "checkAllStudentsAverage()");
    }

    @Test
    public void checkMentorStudents() {
        MentorStudent expected = new MentorStudent("Yaroslav", Arrays.asList("Mykola","Denys"));
        MentorStudent actual = marathonService.mentorStudents("Yaroslav");
        Assertions.assertEquals(expected, actual, "checkMentorStudents()");

    }
}
