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
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.stream.Collectors;

@Controller
@Data
public class MarathonController {
    private MarathonService marathonService;
    private UserService studentService;

    Logger logger = LogManager.getLogger(MarathonController.class);


    public MarathonController(MarathonService marathonService, UserService studentService) {
        this.marathonService = marathonService;
        this.studentService = studentService;
    }

    @GetMapping("/create-marathon")
    public String createMarathon(Model model) {

        logger.info("Inside /create-marathon");
        model.addAttribute("marathon", new Marathon());
        return "create-marathon";
    }

    private void logErrors(BindingResult result) {
        String err = result.getAllErrors().stream().map(e -> e.toString()).collect(Collectors.joining("\n"));
        logger.error(err);
    }

    @PostMapping("/marathons")
    public String createMarathon(@Validated @ModelAttribute Marathon marathon, BindingResult result) {

        logger.info("Inside /marathons");
        if (result.hasErrors()) {
            logErrors(result);
            return "create-marathon";
        }




        marathonService.createOrUpdate(marathon);
        return "redirect:/marathons";
    }

    @GetMapping("/marathons/edit/{id}")
    public String updateMarathon(@PathVariable long id, Model model) {
        logger.info("Inside /marathons/edit/{id} GetMapping");
        Marathon marathon = marathonService.getMarathonById(id);
        model.addAttribute("marathon", marathon);
        return "update-marathon";
    }

    @PostMapping("/marathons/edit/{id}")
    public String updateMarathon(@PathVariable long id, @ModelAttribute Marathon marathon, BindingResult result) {
        logger.info("Inside /marathons/edit/{id} PostMapping");
        if (result.hasErrors()) {
            logErrors(result);
            return "update-marathon";
        }
        marathonService.createOrUpdate(marathon);
        return "redirect:/marathons";
    }

    @GetMapping("/marathons/delete/{id}")
    public String deleteMarathon(@PathVariable long id) {
        logger.info("Inside /marathons/delete/{id}");
        marathonService.deleteMarathonById(id);
        return "redirect:/marathons";
    }

    @GetMapping("/students/{marathon_id}")
    public String getStudentsFromMarathon(@PathVariable("marathon_id") long marathonId, Model model) {
        logger.info("Inside /students/{marathon_id}");
        List<User> students = studentService.getAll().stream().filter(
                student -> student.getMarathons().stream().anyMatch(
                        marathon -> marathon.getId() == marathonId)).collect(Collectors.toList());
        Marathon marathon = marathonService.getMarathonById(marathonId);
        model.addAttribute("students", students);
        model.addAttribute("all_students", studentService.getAll());
        model.addAttribute("marathon", marathon);
        return "marathon-students";
    }

    @GetMapping("/marathons")
    public String getAllMarathons(Model model) {
        logger.info("Inside /marathons");
        List<Marathon> marathons = marathonService.getAll();
        model.addAttribute("marathons", marathons);
        return "marathons";
    }

}
