package com.softserve.edu.controller;

import com.softserve.edu.model.Marathon;
import com.softserve.edu.service.MarathonService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Data
public class MarathonController {

    @Autowired
    private MarathonService marathonService;

    @GetMapping("/marathons")
    public String getAllMarathons(Model model) {
        return "marathons-list";
    }

    @GetMapping("/marathons/delete/{id}")
    public String deleteMarathon(@PathVariable(name = "id") Long id) {
        marathonService.deleteMarathonById(id);
        return "redirect:/marathons";
    }

    @PostMapping("/marathons")
    public String marathonCreationPage(Model model) {
        model.addAttribute("marathon", new Marathon());
        return "marathon";
    }

    @PostMapping("/marathons/create")
    public String createMarathon(Model model, @ModelAttribute("marathon") Marathon marathon) {
        if (marathon.getTitle() != null && !marathon.getTitle().isBlank())
            marathonService.createOrUpdate(marathon);
        return "redirect:/marathons";
    }

    @GetMapping("/marathons/edit/{marathonId}")
    public String editMarathon(Model model, @PathVariable("marathonId") Long marathonId) {
        model.addAttribute("marathon", marathonService.getMarathonById(marathonId));
        return "marathon";
    }

    @PostMapping("/marathons/edit/{marathonId}")
    public String saveEditedMarathon(Model model,
                                     @PathVariable("marathonId") Long marathonId,
                                     @ModelAttribute("marathon") Marathon marathon) {
        marathon.setId(marathonId);
        marathonService.createOrUpdate(marathon);
        return "redirect:/marathons";
    }

    @ModelAttribute("allMarathons")
    public List<Marathon> getAllMarathons() {
        return marathonService.getAll();
    }
}
