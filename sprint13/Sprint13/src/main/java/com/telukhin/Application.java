package com.telukhin;

import com.telukhin.domain.Marathon;
import com.telukhin.domain.User;
import com.telukhin.service.MarathonService;
import com.telukhin.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private UserService userService;
    private MarathonService marathonService;

    public Application(UserService userService, MarathonService marathonService) {
        this.userService = userService;
        this.marathonService = marathonService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("=======   application started   ========");

        int mentorsNumber = 3;
        int studentsNumber = 10;
        int marathonNumber = 4;
        createSeveralUsers(mentorsNumber, User.Role.MENTOR);
        createSeveralUsers(studentsNumber, User.Role.STUDENT);
        createSeveralMarathons(marathonNumber);
        showAllMentors();
        showAllStudents();
//        marathonService.deleteMarathonById(1L);
        addUsersToMarathon();
        showAllMarathons();
    }

    private void addUsersToMarathon() {
        int userNumber = userService.getAll().size();
        int marathonNumber = marathonService.getAll().size();
        Random randomUserId = new Random();
        Random randomMarathonId = new Random();
        for (int i = 1; i < userNumber; i++) {
            User user = userService.getUserById( (long) randomUserId.nextInt(userNumber) + 1);
            Marathon marathon = marathonService.getMarathonById((long) randomMarathonId.nextInt(marathonNumber) + 1);
            userService.addUserToMarathon(user, marathon);
        }
    }


    private void showAllMarathons() {
        System.out.println("All marathons list:");
        System.out.println(marathonService.getAll());
        System.out.println("----------------------------");
    }

    private void createSeveralMarathons(int marathonNumber) {
        Marathon marathon = null;
        for (int i = 1; i < marathonNumber; i++) {
            try {
                marathon = new Marathon();
                marathon.setTitle("JOM" + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            marathonService.createOrUpdate(marathon);
        }
    }

    private void showAllMentors() {
        System.out.println("All mentors list:");
        System.out.println(userService.getAllByRole(User.Role.MENTOR.toString()));
        System.out.println("----------------------------");
    }

    private void showAllStudents() {
        System.out.println("All students list:");
        System.out.println(userService.getAllByRole(User.Role.STUDENT.toString()));
        System.out.println("----------------------------");
    }
    private void createSeveralUsers(int usersNumber, User.Role role) {
        User user1 = null;
        for (int i = 1; i <= usersNumber; i++) {
            try {
                user1 = new User();
                user1.setEmail(role.toString().toLowerCase() + "Email" + i + "@gmail.com");
                user1.setFirstName(role.toString().toLowerCase() + "Name" + i);
                user1.setLastName(role.toString().toLowerCase() + "LastName" + i);
                user1.setPassword("password" + i);
                user1.setRole(role);
            } catch (Exception e) {
                e.printStackTrace();
            }
            userService.createOrUpdateUser(user1);
        }
    }
}