package com.softserve.edu.controller;

        import com.softserve.edu.model.Marathon;
        import com.softserve.edu.model.User;
        import com.softserve.edu.service.MarathonService;
        import com.softserve.edu.service.UserService;
        import lombok.Data;
        import org.apache.logging.log4j.LogManager;
        import org.apache.logging.log4j.Logger;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.BindingResult;
        import org.springframework.validation.annotation.Validated;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.stream.Collectors;

@Controller
@Data
public class StudentController {

    private UserService studentService;
    private MarathonService marathonService;

    Logger logger = LogManager.getLogger(MarathonController.class);

    public StudentController(UserService studentService, MarathonService marathonService) {
        this.studentService = studentService;
        this.marathonService = marathonService;
    }

    private void logErrors(BindingResult result) {
        String err = result.getAllErrors().stream().map(e -> e.toString()).collect(Collectors.joining("\n"));
        logger.error(err);
    }

    @GetMapping("/create-student")
    public String createStudent(Model model) {
        logger.info("Inside /create-student");
        model.addAttribute("user", new User());
        return "create-student";
    }

    @PostMapping("students/{marathon_id}/add")
    public String createStudent(@PathVariable("marathon_id") long marathonId, @Validated @ModelAttribute User user, BindingResult result) {
        logger.info("Inside students/{marathon_id}/add PostMapping");
        if (result.hasErrors()) {
            logErrors(result);
            return "create-student";
        }
        studentService.addUserToMarathon(
                studentService.createOrUpdateUser(user),
                marathonService.getMarathonById(marathonId));
        return "redirect:/students/" + marathonId;
    }

    @GetMapping("students/{marathon_id}/add")
    public String createStudent(@RequestParam("user_id") long userId, @PathVariable("marathon_id") long marathonId) {
        logger.info("Inside students/{marathon_id}/add GetMapping");
        studentService.addUserToMarathon(
                studentService.getUserById(userId),
                marathonService.getMarathonById(marathonId));
        return "redirect:/students/" + marathonId;
    }

    @GetMapping("/students/{marathon_id}/edit/{student_id}")
    public String updateStudent(@PathVariable("marathon_id") long marathonId, @PathVariable("student_id") long studentId, Model model) {
        logger.info("Inside /students/{marathon_id}/edit/{student_id} GetMapping");
        User user = studentService.getUserById(studentId);
        model.addAttribute("user", user);
        return "update-student";
    }

    @PostMapping("/students/{marathon_id}/edit/{student_id}")
    public String updateStudent(@PathVariable("marathon_id") long marathonId, @PathVariable("student_id") long studentId, @Validated @ModelAttribute User user, BindingResult result) {
        logger.info("Inside /students/{marathon_id}/edit/{student_id} GetMapping");
        if (result.hasErrors()) {
            logErrors(result);
            return "update-marathon";
        }
        studentService.createOrUpdateUser(user);
        return "redirect:/students/" + marathonId;
    }

    @GetMapping("/students/{marathon_id}/delete/{student_id}")
    public String deleteStudent(@PathVariable("marathon_id") long marathonId, @PathVariable("student_id") long studentId) {
        studentService.deleteUserFromMarathon(
                studentService.getUserById(studentId),
                marathonService.getMarathonById(marathonId));
        return "redirect:/students/" + marathonId;
    }

    @GetMapping("/students")
    public String getAllStudents(Model model) {
        logger.info("Inside /students");
        List<User> students = studentService.getAll();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable long id, Model model) {
        logger.info("Inside /students/edit/{id} GetMapping");
        User user = studentService.getUserById(id);
        model.addAttribute("user", user);
        return "update-student";
    }

    @PostMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable long id, @Validated @ModelAttribute User user, BindingResult result) {
        logger.info("Inside /students/edit/{id} PostMapping");
        if (result.hasErrors()) {
            logErrors(result);
            return "update-marathon";
        }
        studentService.createOrUpdateUser(user);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable long id) {
        logger.info("Inside /students/delete/{id}");
        User student = studentService.getUserById(id);
        for (Marathon marathon : student.getMarathons()) {
            studentService.deleteUserFromMarathon(student, marathon);
        }
        studentService.deleteUserById(id);
        return "redirect:/students";
    }
}
