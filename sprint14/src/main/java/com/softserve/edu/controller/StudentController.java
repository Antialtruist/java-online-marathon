package com.softserve.edu.controller;

import com.softserve.edu.model.User;
import com.softserve.edu.service.MarathonService;
import com.softserve.edu.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@Data
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private MarathonService marathonService;

    @GetMapping("/students")
    public String listAllStudents(Model model) {
        model.addAttribute("students", userService.getAllByRole(User.Role.TRAINEE));
        model.addAttribute("marathon", null);
        return "students-list";
    }

    @GetMapping("/students/{marathon_id}")
    public String listStudentsByMarathon(Model model,
           @PathVariable(name = "marathon_id") Long marathonId)
    {
        model.addAttribute("students", userService.getStudentsByMarathon(marathonId));
        model.addAttribute("marathon", marathonService.getMarathonById(marathonId));
        return "students-list";
    }

    @GetMapping("/students/{marathon_id}/add")
    public String openStudentCreationPage(Model model,
                                         @PathVariable(name = "marathon_id") Long marathonId)
    {
        model.addAttribute("student", new User());
        model.addAttribute("marathonId", marathonId);
        return "student";
    }

    @PostMapping("/students/{marathon_id}/add")
    public String addNewStudentToMarathon(Model model,
                                          @PathVariable("marathon_id") Long marathonId,
                                          @ModelAttribute("student") User student)
    {
        student.setRole(User.Role.TRAINEE);
        userService.createOrUpdateUser(student);
        userService.addUserToMarathon(student, marathonService.getMarathonById(marathonId));
        return "redirect:/students/" + marathonId;
    }

    @GetMapping("/students/{marathon_id}/delete/{student_id}")
    public String removeStudentFromMarathon(@PathVariable(name = "marathon_id") Long marathonId,
                                            @PathVariable(name = "student_id") Long studentId)
    {
        userService.removeFromMarathon(studentId, marathonId);
        return "redirect:/students/{marathon_id}";
    }

    @GetMapping("/students/delete/{student_id}")
    public String deleteStudent(@PathVariable(name = "student_id") Long studentId){
        userService.deleteUserById(studentId);
        return "redirect:/students";
    }

    @GetMapping("/students/{marathon_id}/edit/{student_id}")
    public String editUserByMarathon(Model model,
                                     @PathVariable(name = "marathon_id") Long marathonId,
                                        @PathVariable(name = "student_id") Long studentId)
    {
        model.addAttribute("student", userService.getUserById(studentId));
        model.addAttribute("studentId", studentId);
        model.addAttribute("marathonId", marathonId);
        model.addAttribute("update", true);
        return "student";
    }

    @GetMapping("/students/edit/{student_id}")
    public String editStudent(Model model, @PathVariable(name = "student_id") Long studentId){
        model.addAttribute("student", userService.getUserById(studentId));
        model.addAttribute("studentId", studentId);
        model.addAttribute("update", true);
        return "student";
    }

    @PostMapping("/students/update/{studentId}")
    public String updateStudent(@PathVariable("studentId") Long studentId, @ModelAttribute("student") User student) {
        student.setRole(User.Role.TRAINEE);
        student.setId(studentId);
        userService.createOrUpdateUser(student);
        return "redirect:/students";
    }

    @PostMapping("/students/{marathonId}/update/{studentId}")
    public String updateStudentInMarathon(
            @PathVariable("marathonId") Long marathonId,
            @PathVariable("studentId") Long studentId,
            @ModelAttribute("student") User student) {
        student.setRole(User.Role.TRAINEE);
        student.setId(studentId);
        userService.createOrUpdateUser(student);
        return "redirect:/students/" + marathonId;
    }
}
